package Pdu;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;


public class PDUInputStream {

    private InputStream inputStream;
    private int opByte;
    private Pdu incomingPdu;

    public PDUInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
        this.incomingPdu = null;
    }

    /**
     * @return The next PDU in the stream.
     *
     * @throws java.io.EOFException If the stream closed without an error.
     * @throws java.io.IOException If the stream closed with an error.
     */
    public Pdu readPdu() throws EOFException, IOException {

        opByte = inputStream.read();

        switch (opByte){
            case 4:
                incomingPdu = new PduSlist(inputStream);
                break;
            case 10:
                //mess
                break;
            case 11:
                //quit
                break;
            case 16:
                incomingPdu = new PduPjoin(inputStream);
                break;
            case 17:
                incomingPdu = new PduPleave(inputStream);
                break;
            case 19:
                incomingPdu = new PduParticipants(inputStream);
                break;
            default:
                //corrupt pdu?
        }
        return incomingPdu;
    }
}
