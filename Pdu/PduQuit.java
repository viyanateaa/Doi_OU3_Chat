/**
 * Created by kristoffer & Viyan on 2016-09-28.
 */
package Pdu;

import java.io.IOException;
import java.io.InputStream;

/**
 * Course: Datakommutikation och internet 5DV167
 * Assignment: OU3
 * Written by: Kristoffer & Viyan
 * Version: 19/1 -17.
 */

/**
 * Class that represents a Quit Pdu.
 */
public class PduQuit extends Pdu {

    private final byte op = 11;

    public PduQuit(){
        sequenceBuilder = new ByteSequenceBuilder(op).pad();
        bytes = sequenceBuilder.toByteArray();
     }

    public PduQuit(InputStream inputStream) throws IOException {
        sequenceBuilder = new ByteSequenceBuilder(op);
        byte[] byteArray = new byte[3];

        //reads the the info of the pdu
        byteArray[0] = (byte)inputStream.read();
        byteArray[1] = (byte)inputStream.read();
        byteArray[2] = (byte)inputStream.read();
        sequenceBuilder.append(byteArray);

        //checks the padding of the pdu
        if(byteArray[0] != 0 || byteArray[1] != 0 || byteArray[2]
                != 0){
            throw new IllegalArgumentException("the padding of the " +
                    "pdu is wrong. PduQuit corrupted! Please exit program.");
        }else {
            bytes = sequenceBuilder.toByteArray();
        }
    }

    /**
     * Implements the abstract printInfo method. Prints information
     * for the user.
     */
    public void printInfo(){
        System.out.println("A quit request has been recieved, " +
                "exiting application.");
    }
}
