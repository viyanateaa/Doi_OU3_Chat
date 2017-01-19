package Pdu;
import java.io.UnsupportedEncodingException;


/**
 * Course: Datakommutikation och internet 5DV167
 * Assignment: OU3
 * Written by: Kristoffer & Viyan
 * Version: 19/1 -17.
 */

/**
 * Class that represents a Join Pdu
 */
public class PduJoin extends Pdu{
    private final byte op = 12;

    /**
     * Constructor PduJoin
     * @param identity the chosen identity of the user.
     * @throws UnsupportedEncodingException if UTF-8 is not supported.
     */
    public PduJoin(String identity) throws UnsupportedEncodingException {

        sequenceBuilder = new ByteSequenceBuilder(op);
        sequenceBuilder.append((byte)identity.getBytes("UTF-8").length);
        sequenceBuilder.pad();
        sequenceBuilder.append(identity.getBytes("UTF-8")).pad();
        bytes = sequenceBuilder.toByteArray();
    }

    /**
     * Implements the abstract printInfo method.
     */
    public void printInfo(){
        System.out.println("This is a Join pdu with op:12");
    }
}
