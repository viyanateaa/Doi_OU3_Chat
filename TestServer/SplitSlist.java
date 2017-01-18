package TestServer;

import java.io.IOException;

/**
 * Created by kristoffer on 2017-01-18.
 */
public class SplitSlist extends Server {

    public SplitSlist(int port) throws IOException {
        super(port);
    }

    public void returnInfo() throws IOException {
        byte[] sListPart1 = {(byte)4,0,0,2,111,0,0,1,0,22,1,2,'K',
                'G',0,0};
        byte[] sListPart2 = {111,0,0,1,0,33,1,2,'V','A',0,0};
        serverOutputStream.write(sListPart1);
        serverOutputStream.flush();
        serverOutputStream.write(sListPart2);
    }

    public static void main(String[] args) throws IOException {
        new FakeSlist(new Integer(args[0]));
    }

}
