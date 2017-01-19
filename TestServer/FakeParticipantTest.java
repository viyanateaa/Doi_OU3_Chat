package TestServer;

import java.io.IOException;

/**
 * Course: Datakommutikation och internet 5DV167
 * Assignment: OU3
 * Written by: Kristoffer & Viyan
 * Version: 19/1 -17.
 */

/**
 * Class that test if a ChatClient can detect a wrong participants
 * pdu.
 */
public class FakeParticipantTest extends Server {

    public FakeParticipantTest(int port) throws IOException {
        super(port);
    }

    public void serverMessage() throws IOException {
        byte[] fakeParticipantsPdu = {19, 2, 0, 9, 'P', '0', 0, 0,
                'K', 'A', 'L', 'L', 'E', 0, 0, 0};

        serverOutputStream.write(fakeParticipantsPdu);
    }

    public static void main(String[] args) throws IOException {
        new FakeParticipantTest(new Integer(args[0]));
    }
}
