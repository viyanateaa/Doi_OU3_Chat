package TestServer;

import java.io.IOException;

/**
 * Course: Datakommutikation och internet 5DV167
 * Assignment: OU3
 * Written by: Kristoffer & Viyan
 * Version: 19/1 -17.
 */

/**
 * Test Class that creates two part of a participants message and
 * sends them after eachouter.
 */
public class SplitSlistTest extends Server {

    public SplitSlistTest(int port) throws IOException {
        super(port);
    }

    public void serverMessage() throws IOException {
        byte[] sListPart1 = {(byte)4,0,0,2,111,0,0,1,0,22,1,2,'K',
                'G',0,0};
        byte[] sListPart2 = {111,0,0,1,0,33,1,2,'V','A',0,0};
        serverOutputStream.write(sListPart1);
        serverOutputStream.flush();
        serverOutputStream.write(sListPart2);
    }

    public static void main(String[] args) throws IOException {
        new FakeSlistTest(new Integer(args[0]));
    }
}
