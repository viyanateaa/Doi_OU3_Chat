/**
 * Created by kristoffer & Viyan on 2016-09-28.
 */
package Pdu;
public class PduQuit extends Pdu {

    private final byte op = 11;


    //Not done!
    public PduQuit(){
        sequenceBuilder = new ByteSequenceBuilder(op);
        sequenceBuilder.pad();
        bytes= sequenceBuilder.toByteArray();
     }
}
