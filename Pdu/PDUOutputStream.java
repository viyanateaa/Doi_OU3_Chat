package Pdu;

import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by viyanateaa on 2016-12-22.
 */
public class PDUOutputStream {

    private OutputStream outputStream;

    public PDUOutputStream(OutputStream outputStream){
        this.outputStream=outputStream;
    }

    public void sendPDU(Pdu Pdu) throws IOException {
        outputStream.write(Pdu.getBytes());
    }

}
