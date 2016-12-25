package Pdu;
import java.io.UnsupportedEncodingException;

/**
 * Created by kristoffer & Viyan on 2016-09-28.
 */
public class PduJoin extends Pdu{

    private final byte op = 12;

    /**
     *
     * @param identity
     * @throws UnsupportedEncodingException if UTF-8 is not supported.
     */
    public PduJoin(String identity) throws UnsupportedEncodingException {

        sequenceBuilder = new ByteSequenceBuilder(op);
        sequenceBuilder.append((byte)identity.getBytes("UTF-8")
                .length);
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
