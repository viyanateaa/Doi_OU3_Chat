/**
 * Created by kristoffer & Viyan on 2016-09-28.
 */
package Pdu;

import java.io.InputStream;

/**
 * Abstract class that represents pdu.
 */
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
     * @param inputStream of incomming pdu.
     */
    public Pdu (InputStream inputStream){

    }

    /**
     * gets the byte array contained in pdu
     * @return a byte array called bytes.
     */
    public byte[] getBytes() {
        return bytes;
    }

    /**
     * Abstract method that print the information of a pdu.
     */
    abstract public void printInfo();

    public ByteSequenceBuilder getSequenceBuilder() {
        return sequenceBuilder;
    }
}

