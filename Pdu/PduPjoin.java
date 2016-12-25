package Pdu;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;


//// Not Done!
public class PduPjoin extends Pdu{

    private final byte op =16;
    private String Identity;
    private long unixTime;
    InputStream in;

    public PduPjoin(InputStream inputStream) throws UnsupportedEncodingException {

        sequenceBuilder = new ByteSequenceBuilder(op);

        in = inputStream;


        sequenceBuilder.append((byte)Identity.getBytes("UTF-8").length).pad();
        sequenceBuilder.append(Identity.getBytes("UTF-8")).pad();
        sequenceBuilder.append((byte)timeStamp.getBytes("UTF-8").length);
        sequenceBuilder.append((byte)clientIdentity.getBytes("UTF-8").length);



        //bytes= sequenceBuilder.toByteArray();



    }
}
