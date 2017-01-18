package TestServer;

import java.io.IOException;

/**
 * Class that simulates a fake nameserver and sends wrong Slist Pdu.
 * Created by kristoffer on 2017-01-18.
 */
public class FakeSlist extends Server {

    public FakeSlist(int port) throws IOException {
        super(port);
    }

    public void returnInfo() throws IOException {
        byte[] fakeSlist = {(byte)4,0,0,2,111,0,0,1,0,22,1,2,'K','G',
                0,1,111,0,0,1,0,33,1,2,'V','A',0,0};
        serverOutputStream.write(fakeSlist);
    }

    public static void main(String[] args) throws IOException {
        new FakeSlist(new Integer(args[0]));
    }

}
