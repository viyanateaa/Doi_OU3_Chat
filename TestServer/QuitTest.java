package TestServer;

import Pdu.PduQuit;
import Pdu.Pdu;

import java.io.IOException;

/**
 * Course: Datakommutikation och internet 5DV167
 * Assignment: OU3
 * Written by: Kristoffer & Viyan
 * Version: 19/1 -17.
 */

/**
 * Test class that test that a chatClient shuts down when receiving a
 * quit pdu.
 */
public class QuitTest extends Server{

    public QuitTest(int port) throws IOException {
        super(port);
    }

    public void serverMessage() throws IOException {
        Pdu quitPdu = new PduQuit();
        serverOutputStream.write(quitPdu.getBytes());
    }

    public static void main(String[] args) throws IOException {
        new QuitTest(new Integer(args[0]));
    }
}
