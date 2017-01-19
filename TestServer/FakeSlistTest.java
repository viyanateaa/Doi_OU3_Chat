package TestServer;

import java.io.IOException;

/**
 * Course: Datakommutikation och internet 5DV167
 * Assignment: OU3
 * Written by: Kristoffer & Viyan
 * Version: 19/1 -17.
 */

/**
 * Class that simulates a fake nameserver and sends wrong Slist Pdu.
 */
public class FakeSlistTest extends Server {

    public FakeSlistTest(int port) throws IOException {
        super(port);
    }

    public void serverMessage() throws IOException {
        byte[] fakeSlist = {(byte)4,0,0,2,111,0,0,1,0,22,1,2,'K','G',
                0,1,111,0,0,1,0,33,1,2,'V','A',0,0};
        serverOutputStream.write(fakeSlist);
    }

    public static void main(String[] args) throws IOException {
        new FakeSlistTest(new Integer(args[0]));
    }

}
