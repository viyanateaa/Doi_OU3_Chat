package Pdu;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.Date;
/**
 * Course: Datakommutikation och internet 5DV167
 * Assignment: OU3
 * Written by: Kristoffer & Viyan
 * Version: 19/1 -17.
 */

/**
 * Class that represents a Message Pdu.
 */
public class PduMess extends Pdu {

    private final byte op = 10;
    private String identity;
    private String message;
    private int identityLength;
    private int messageLength;
    private int checksum;
    private Date timeStamp;

    /**
     * The constructor for a PduMess to send.
     * @param clientIdentity chosen by the user.
     * @param message to be sent.
     * @throws UnsupportedEncodingException i UTF-8 is not supported.
     */

    public PduMess(String clientIdentity, String message) throws UnsupportedEncodingException {
        this.message = message;
        this.identity = clientIdentity;

        sequenceBuilder = new ByteSequenceBuilder(op);
        sequenceBuilder.append((byte)0);


        // Add ID length.
        sequenceBuilder.append((byte)identity.getBytes("UTF-8")
                .length);

        // check sum
        sequenceBuilder.append((byte)0);

        //messagelength + pad
        sequenceBuilder.appendShort((byte)message.getBytes("UTF-8")
                .length).pad();

        //Timestamp (added by server, so now only 0)
        sequenceBuilder.appendInt(0);

        //adds message + pad and client ID + pad
        sequenceBuilder.append(message.getBytes("UTF-8")).pad();
        sequenceBuilder.append(identity.getBytes("UTF-8")).pad();

        bytes = sequenceBuilder.toByteArray();

        //computes and puts in the checksum.
        bytes[3] = Checksum.computeChecksum(bytes);
    }

    /**
     * The constructor for a received PduMess.
     * @param inputStream were the PduMess is received from
     * @throws IOException if unable to read from the inputStream.
     */
    public PduMess(InputStream inputStream) throws IOException {
        byte[] byteArray;
        sequenceBuilder = new ByteSequenceBuilder(op);

        //Checks the first instance of padding
        byteArray = new byte[1];
        byteArray[0] = (byte)inputStream.read();
        if (byteArray[0] != 0){
            throw new IllegalArgumentException("the padding of the " +
                    "Pdu is wrong. Message corrupted!");
        }
        sequenceBuilder.append(byteArray);

        //gets identity length.
        byteArray[0] = (byte)inputStream.read();
        sequenceBuilder.append(byteArray);
        identityLength = byteArray[0];

        //gets the checksum.
        byteArray[0] = (byte)inputStream.read();
        sequenceBuilder.append(byteArray);
        checksum = byteArray[0];

        //gets the message length
        byteArray = new byte[2];
        byteArray[0] = (byte)inputStream.read();
        byteArray[1] = (byte)inputStream.read();
        messageLength = ((byteArray[0]& 0xff) <<8) | (byteArray[1] & 0xff);
        sequenceBuilder.append(byteArray);

        //there should be padding next, checks it.
        byteArray[0] = (byte)inputStream.read();
        byteArray[1] = (byte)inputStream.read();
        if (byteArray[0] != 0 || byteArray[1] != 0){
            throw new IllegalArgumentException("the padding of the " +
                    "Pdu is wrong. Message corrupted! Please exit program.");
        }
        sequenceBuilder.append(byteArray);

        //gets the timestamp and converts it to standard date format.
        byteArray = new byte[4];
        inputStream.read(byteArray,0,4);
        long unixTime = ByteBuffer.wrap(byteArray).getInt();
        timeStamp = new Date(unixTime*1000);
        sequenceBuilder.append(byteArray);

        //gets the message.
        byteArray = new byte[messageLength];
        inputStream.read(byteArray,0,messageLength);
        message = new String(byteArray, StandardCharsets.UTF_8);
        sequenceBuilder.append(byteArray);

        //takes care of padding if there is any.
        byteArray = new byte[1];
        if (!(messageLength %4 == 0)){
            for(int i = 0;i < (4 - (messageLength%4));i++){
                byteArray[0] = (byte)inputStream.read();
                if(byteArray[0] != 0){
                    throw new IllegalArgumentException("the padding" +
                            " of the Pdu is wrong. Message " +
                            "corrupted! Please exit program.");
                }
                sequenceBuilder.append(byteArray);
            }
        }

        //gets the identity.
        byteArray = new byte[identityLength];
        inputStream.read(byteArray,0,identityLength);
        sequenceBuilder.append(byteArray);
        identity = new String(byteArray,StandardCharsets.UTF_8);

        //takes care of any remaining padding if there is any.
        byteArray = new byte[1];
        if (!(identityLength%4 == 0)){
            for(int i = 0;i < (4 - (identityLength%4));i++){
                byteArray[0] = (byte)inputStream.read();
                if(byteArray[0] != 0){
                    throw new IllegalArgumentException("the padding" +
                            " of the Pdu is wrong. Message " +
                            "corrupted! Please exit program.");
                }
                sequenceBuilder.append(byteArray);
            }
        }
        bytes = sequenceBuilder.toByteArray();
        checksumTest(bytes);
    }

    /**
     * Method that test the checksum of a message.
     * @param bytes of the Message Pdu.
     */
    public void checksumTest(byte[] bytes){
        int test = Checksum.computeChecksum(bytes);
        if(test != 0){
            throw new IllegalArgumentException("The checksum is not" +
                    " correct. message corrupt! Please exit program.");
        }
    }
    /**
     * method that prints the info of the Pdu.
     */
    public void printInfo(){
        System.out.println(timeStamp + " [" + identity +"]");
        System.out.println(message);
    }
}