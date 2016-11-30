package Pdu;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;

/**
 * Created by kristoffer & Viyan on 2016-09-28.
 */
//// TODO: 2016-10-11 test the class, time converting
public class PduMess extends Pdu {

    private final byte op = 10;
    private String identity;
    private String message;
    private int identityLength;
    private int messageLength;
    private int time;


    private String clientIdentity;
    private  int clientIdentitylength;

    // mess skapas
    public PduMess(String clientIdentity, String message) throws UnsupportedEncodingException {
        /**
         * Creates the header of the pdu.
         */
        sequenceBuilder = new ByteSequenceBuilder(op);
        sequenceBuilder.append((byte)0);

        /*
        add Id CLIENT LENGTH
         */
        // ta in id length som byte
        sequenceBuilder.append((byte)clientIdentity.getBytes("UTF-8").length);
        sequenceBuilder.append((byte)0);
        //check sum

        sequenceBuilder.appendShort((byte)message.getBytes("UTF-8").length).pad();

        //tidstämpel
        sequenceBuilder.appendInt((byte)0);

        sequenceBuilder.append(message.getBytes("UTF-8")).pad();
        sequenceBuilder.append(clientIdentity.getBytes("UTF-8")).pad();

        bytes= sequenceBuilder.toByteArray();

        bytes[4]= Checksum.computeChecksum(bytes);
    }


    public PduMess(InputStream inputStream) throws IOException {
        byte[] temp;

        sequenceBuilder = new ByteSequenceBuilder(op);
        temp= new byte[1];
        inputStream.read(temp,0,1);

        sequenceBuilder.append(temp);

        inputStream.read(temp,0,1);
        sequenceBuilder.append(temp);
        identityLength= temp [0];

        inputStream.read(temp,0,1);
        sequenceBuilder.append(temp);

        temp= new byte[2];
        inputStream.read(temp,0,2);
        messageLength = ((temp[0]& 0xff) <<8) | (temp[1] & 0xff);
        sequenceBuilder.append(temp);

        inputStream.read(temp,0,2);
        sequenceBuilder.append(temp);

        temp= new byte[4];
        inputStream.read(temp,0,4);
        time = ByteBuffer.wrap(temp).getInt();
        sequenceBuilder.append(temp);

        temp = new byte[messageLength];
        inputStream.read(temp,0,messageLength);
        message = new String(temp,"UTF-8");
        sequenceBuilder.append(temp);

        if (!(messageLength %4 ==0 )){
            temp = new byte[4-(messageLength%4)];
            inputStream.read(temp, 0,4-(messageLength%4));
            sequenceBuilder.append(temp);
        }

        temp= new byte[4];
        inputStream.read(temp,0,4);
        sequenceBuilder.append(temp);

        temp = new byte[clientIdentitylength];
        clientIdentity = new String(temp,"UTF-8");
        sequenceBuilder.append(temp);



        //fixa for client samma som förre!














    }
}