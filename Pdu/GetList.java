/**
 * Class that implements the abstract class Pdu
 * creates an pdu operation called GetList.
 *
 * Created by kristoffer on 2016-09-28.
 */
package Pdu;

import java.util.Arrays;

public class GetList extends Pdu {

    private final byte op = 3;

    public GetList(){

        sequenceBuilder = new ByteSequenceBuilder(op);
        sequenceBuilder.pad();
        bytes= sequenceBuilder.toByteArray();
        System.out.println(Arrays.toString(bytes));

    }
}
