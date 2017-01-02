package Pdu;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

/**
 * Created by kristoffer & Viyan on 2016-12-26.
 */
public class PduParticipants extends Pdu{

    private String[] participants;

    public PduParticipants(InputStream inputStream) throws IOException {

        byte nrOfClients = (byte)inputStream.read();
        participants = new String[nrOfClients];
        byte[] lengthArray = new byte[2];

        //collects info about length of pdu
        lengthArray[0] = (byte)inputStream.read();
        lengthArray[1] = (byte)inputStream.read();
        int lengthOfPdu = ((lengthArray[0] & 0xff) | (lengthArray[1]
                & 0xff));

        //split info into separate strings
        for(int i=0;i<nrOfClients;i++){
            byte[] name = new byte[65355];
            int index = 0;
            while (true){
                name[index] = (byte)inputStream.read();
                if(name[index] == 0){
                    break;
                }
                index++;
            }
            participants[i] = new String(name, StandardCharsets
                    .UTF_8);
        }
        // if there is remaining padding takes care of. Checks it
        // as well.
        if(lengthOfPdu%4 != 0){
            for (int j = 0; j < (4 - (lengthOfPdu % 4)); j++) {
                if (inputStream.read() != (byte)0) {
                    throw new IllegalArgumentException("The format " +
                            "for PduParticipants is wrong! corrupt " +
                            "PDU");
                }
            }
        }
    }

    /**
     * Method that prints out the participants of the chat to the
     * user.
     */
    public void printInfo(){
        System.out.println("Participants:");
        for (String participant : participants) {
            System.out.println(participant);
        }
    }
}
