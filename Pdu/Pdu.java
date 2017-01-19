/**
 * Created by kristoffer & Viyan on 2016-09-28.
 */
package Pdu;

import java.io.InputStream;

/**
 * Course: Datakommutikation och internet 5DV167
 * Assignment: OU3
 * Written by: Kristoffer & Viyan
 * Version: 19/1 -17.
 */

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

}

