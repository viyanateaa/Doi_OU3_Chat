/**
 * Created by kristoffer on 2016-10-11.
 */
package Pdu;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.Date;

/**
 * Class that represents a Pleave Pdu.
 */
public class PduPleave extends Pdu{

    private Date timeStamp;
    private String clientIdentity;

    public PduPleave(InputStream inputStream) throws IOException {
        byte identityLenght = (byte)inputStream.read();

        //checks the padding of the pdu
        for(int i= 0;i < 2;i++) {
            if (inputStream.read() != (byte)0) {
                throw new IllegalArgumentException("the format of " +
                        "the PDU is wrong.");
            }
        }

        //gets the timestamp
        byte[] timeArray = new byte[4];
        for(int j = 0;j < 4;j++){
            timeArray[j] = (byte)inputStream.read();
        }
        long unixTime = ByteBuffer.wrap(timeArray).getInt();
        timeStamp = new Date(unixTime);

        //gets the client ID
        byte[] clientId = new byte[identityLenght];
        for(int k = 0;k < identityLenght;k++){
            clientId[k] = (byte)inputStream.read();
        }
        clientIdentity = new String(clientId, StandardCharsets.UTF_8);

        //checks if there is padding
        if(identityLenght%4 != 0){
            for(int i = 0;i < (4 - (identityLenght%4));i++){
                if(inputStream.read() != 0){
                    throw new IllegalArgumentException("The format " +
                            "of the PDU is wrong.");
                }
            }
        }
    }

    /**
     * Method that prints the info of the pdu.
     */
    public void printInfo(){
        System.out.println(timeStamp);
        System.out.println(clientIdentity + " has left the chat.");
    }

    public Date getTimeStamp() {
        return timeStamp;
    }

    public String getClientIdentity() {
        return clientIdentity;
    }
}
