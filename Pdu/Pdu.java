/**
 * Created by kristoffer & Viyan on 2016-09-28.
 */
package Pdu;

import java.io.InputStream;

public abstract class Pdu {


    protected byte[] bytes;
    protected ByteSequenceBuilder sequenceBuilder;

    /**
     * Abstract constructor for outgoing pdu.
     */
    public Pdu(){

    }

    /**
     * Abstract constructor for incoming Pdu
     * @param inputStream inputstream of the pdu.
     */
    public Pdu (InputStream inputStream){

    }

    public byte[] getBytes() {
        return bytes;
    }

    /**
     * Abstract method that print the information of a pdu.
     */
    abstract public void printInfo();
}

