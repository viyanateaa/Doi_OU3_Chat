package Pdu;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

public class PDUInputStream {

    Scanner pduScanner;

    public PDUInputStream(InputStream inputStream) {
        pduScanner = new Scanner(inputStream);
    }

    /**
     * @return The next PDU in the stream.
     *
     * @throws java.io.EOFException If the stream closed without an error.
     * @throws java.io.IOException If the stream closed with an error.
     */
    //// TODO: 2016-10-11 much more...
    public Pdu readPdu() throws EOFException, IOException {

        return null;
    }
}
