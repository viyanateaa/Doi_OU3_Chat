/**
 * Created by kristoffer & Viyan on 2016-09-28.
 */
package Pdu;

import java.io.InputStream;

public abstract class Pdu {


    protected byte[] bytes;
    protected ByteSequenceBuilder sequenceBuilder;

    public Pdu(){

    }

    public Pdu (InputStream inputStream){

    }



    public byte[] getBytes() {
        return bytes;
    }
}

