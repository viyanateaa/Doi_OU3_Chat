/**
 * Created by kristoffer & Viyan on 2016-09-28.
 */
package Pdu;
public class PduQuit extends Pdu {

    private final byte op = 11;


    //// TODO: 2016-12-25 create constructor for receiving quit_pdu
    //// TODO: quit message to be printed for user
    public PduQuit(){
        sequenceBuilder = new ByteSequenceBuilder(op).pad();
        bytes = sequenceBuilder.toByteArray();
     }

    /**
     * Implements the abstract printInfo method. Prints info for
     * the user.
     */
    public void printInfo(){
        System.out.println("Quit message to be printed to screen");
    }
}
