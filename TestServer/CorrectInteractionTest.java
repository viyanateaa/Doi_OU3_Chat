package TestServer;

import Pdu.PduMess;
import Pdu.PduQuit;

import java.io.IOException;

/**
 * Course: Datakommutikation och internet 5DV167
 * Assignment: OU3
 * Written by: Kristoffer & Viyan
 * Version: 19/1 -17.
 */

/**
 * Test class that test that a chatClient can receive PduParticipant,
 * PduPjoin,PduMess,pduPleave and pduQuit.
 */
public class CorrectInteractionTest extends Server{

    private int i = 0;

    public CorrectInteractionTest(int port) throws IOException {
        super(port);
    }

    public void serverMessage() throws IOException {

        byte[] participantsPdu = {(byte)19, 1, 0, 7, 'K', 'R','I',
                'L','L', 'E',0, 0};
        byte[] pJoinPdu = {(byte)16 ,6, 0, 0, 0, 0, 0, 0
                ,'K','R','I','L','L','E',0,0};
        byte[] pLeavePdu = {(byte)17 ,6, 0, 0, 0, 0, 0, 0
                ,'K','R','I','L','L','E',0,0};
        PduMess message = new PduMess("KRILLE","Hej då!");

        if(i == 0){
            serverOutputStream.write(participantsPdu);
            serverOutputStream.write(pJoinPdu);
            i++;
        }else {
            serverOutputStream.write(message.getBytes());
            serverOutputStream.write(pLeavePdu);
            serverOutputStream.write(new PduQuit().getBytes());
            System.exit(0);
        }
    }

    public static void main(String[] args) throws IOException {
        new CorrectInteractionTest(new Integer(args[0]));
    }

}
