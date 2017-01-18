package Pdu;

/**
 * Class that represents a corrupt pdu.
 * Created by kristoffer and Viyan on 2017-01-17.
 */
public class PduCorrupt extends Pdu {

    public PduCorrupt(){}

    /**
     * Method that prints info for the user.
     */
    public void printInfo(){
        System.out.println("a corrupt PDU has been received. \n " +
                "Exiting application.");
    }
}
