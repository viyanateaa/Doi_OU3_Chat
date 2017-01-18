package TestServer;

import java.io.IOException;

/**
 * Created by kristoffer on 2017-01-18.
 */

    public class WrongPduSent extends Server {
        int i = 0;

        public WrongPduSent(int port) throws IOException {
            super(port);
        }

        @Override
        public void returnInfo() throws IOException {

            byte[] participantsPdu = {19, 2, 0, 9, 'P', '0', 0, 'K',
                    'A', 'L','L', 'E', 0, 0, 0, 0};
            byte[] randomPdu = {(byte)25,70,15,54,64,0,0,0,1,2};

            if (i == 0) {
                serverOutputStream.write(participantsPdu);
                i++;
            } else
                serverOutputStream.write(randomPdu);
        }

    public static void main(String[] args) throws IOException {
        new WrongPduSent(new Integer(args[0]));
    }
    }

