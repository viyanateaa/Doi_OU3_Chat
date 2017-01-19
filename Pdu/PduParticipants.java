package Pdu;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Course: Datakommutikation och internet 5DV167
 * Assignment: OU3
 * Written by: Kristoffer & Viyan
 * Version: 19/1 -17.
 */

/**
 * Class that represents a Participants Pdu.
 */

public class PduParticipants extends Pdu{

    private List<String> participantsList;
    private int nrOfClients;

    public PduParticipants(InputStream inputStream) throws IOException {
        sequenceBuilder = new ByteSequenceBuilder((byte)19);

        //collects info about nr of participantsList.
        byte clientNr = (byte)inputStream.read();
        this.nrOfClients = clientNr;
        sequenceBuilder.append(clientNr);

        participantsList = new ArrayList<>();

        //collects info about length of participantsList.
        byte[] lenghtArray = new byte[2];
        lenghtArray[0] = (byte)inputStream.read();
        lenghtArray[1] = (byte)inputStream.read();

        int lenghtOfParticipants = ((lenghtArray[0] & 0xff) << 8 |
                (lenghtArray[1] & 0xff));
        sequenceBuilder.append(lenghtArray);

        //split info into separate strings
        byte[] byteArray = new byte[lenghtOfParticipants];
        inputStream.read(byteArray,0,lenghtOfParticipants);
        int offset = 0;
        for(int i = 0;i < lenghtOfParticipants;i++){
            if(byteArray[i] == 0){
                //checks so names are null terminated only once.
                if((i - offset) > 0){
                    participantsList.add(new String(Arrays.copyOfRange
                            (byteArray,offset,i),StandardCharsets.UTF_8));
                    offset = i + 1;
                }else {
                    throw new IllegalArgumentException("the pdu is " +
                            "corrupt! Please exit program.");
                }

            }
        }
        sequenceBuilder.append(byteArray);
        // takes care of if there is remaining padding. Checks it
        // as well.
        if(lenghtOfParticipants%4 != 0){
            for (int j = 0; j < (4 - (lenghtOfParticipants % 4)); j++) {
                byte testByte = (byte)inputStream.read();
                if (testByte != (byte)0) {
                    throw new IllegalArgumentException("The format " +
                            "for PduParticipants is wrong! corrupt " +
                            "PDU. Please exit program.");
                }
                sequenceBuilder.append(testByte);
            }
        }
        bytes = sequenceBuilder.toByteArray();
    }

    /**
     * Method that prints out the participantsList of the chat to the
     * user.
     */
    public void printInfo(){

        for (int i = 0; i < nrOfClients; i++) {
            System.out.println("Participant nr: "+ (i + 1));
            System.out.println("Client name: "+ participantsList.get
                    (i));
        }
    }

    public List<String> getParticipantsList() {
        return participantsList;
    }
}
