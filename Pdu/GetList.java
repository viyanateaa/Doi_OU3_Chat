/**
 * Class that implements the abstract class Pdu
 * creates an pdu operation called GetList.
 *
 * Created by kristoffer on 2016-09-28.
 */
package Pdu;


public class GetList extends Pdu {

    private final byte op = 3;

    public GetList(){

        sequenceBuilder = new ByteSequenceBuilder(op).pad();
        bytes = sequenceBuilder.toByteArray();
    }

    /**
     * Implements the printInfo method.
     */
    public void printInfo(){
        System.out.println("This is a GetList PDU with OP:3");
    }


}
