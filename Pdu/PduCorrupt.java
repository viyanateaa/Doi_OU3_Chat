package Pdu;

/**
 * Course: Datakommutikation och internet 5DV167
 * Assignment: OU3
 * Written by: Kristoffer & Viyan
 * Version: 19/1 -17.
 */

/**
 * Class that represents a corrupt pdu.
 */
public class PduCorrupt extends Pdu {

    public PduCorrupt(){}

    /**
     * Method that prints info for the user.
     */
    public void printInfo(){
        System.out.println("a corrupt PDU has been received. \n" +
                "Exiting application.");
    }
}
