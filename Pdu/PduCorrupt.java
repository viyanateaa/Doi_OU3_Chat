package Pdu;

/**
 * Created by kristoffer on 2017-01-17.
 */
public class PduCorrupt extends Pdu {

    public PduCorrupt(){}

    public void printInfo(){
        System.out.println("a corrupt PDU has been received. \n " +
                "Exiting application.");
    }
}
