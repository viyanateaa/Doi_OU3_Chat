/**
 * Class that implements the abstract class Pdu
 * creates an pdu operation called GetList.
 *
 * Created by kristoffer on 2016-09-28.
 */
package Pdu;
/**
 * Course: Datakommutikation och internet 5DV167
 * Assignment: OU3
 * Written by: Kristoffer & Viyan
 * Version: 19/1 -17.
 */

/**
 * Class that represents Getlist Pdu.
 */
public class GetList extends Pdu {

    private final byte op = 3;

    public GetList(){

        sequenceBuilder = new ByteSequenceBuilder(op).pad();
        bytes = sequenceBuilder.toByteArray();
    }

    /**
     * Implements the abstract printInfo method.
     */
    public void printInfo(){
        System.out.println("This is a GetList PDU with OP:3");
    }


}
