package TestServer;

import java.io.IOException;

/**
 * Course: Datakommutikation och internet 5DV167
 * Assignment: OU3
 * Written by: Kristoffer & Viyan
 * Version: 19/1 -17.
 */

/**
 * A test class that test that a client can detect when a wrong pdu
 * is sent in the middle of a session.
 */
public class WrongPduMessSentTest extends Server {
    private int i = 0;

    public WrongPduMessSentTest(int port) throws IOException {
        super(port);
    }
    @Override
    public void serverMessage() throws IOException {

        byte[] participantsPdu = {19, 2, 0, 9, 'P', '0', 0, 'K',
                'A', 'L','L', 'E', 0, 0, 0, 0};
        byte[] randomPdu = {(byte)25,70,15,54,64,0,0,0,1,2};

        if (i == 0) {
            serverOutputStream.write(participantsPdu);
            i++;
        } else if(i == 1){
            serverOutputStream.write(randomPdu);
        }
    }
    public static void main(String[] args) throws IOException {
        new WrongPduMessSentTest(new Integer(args[0]));
    }
}

