/**
 * Created by kristoffer on 2016-10-11.
 */
package Pdu;

import java.nio.ByteBuffer;

//// Not Done!!
public class PduSlist extends Pdu {

    //int nrOfServers;
    /////some

    public PduSlist(byte [] in){


       byte [] nrOfServers= new byte [2];


        for (int i=2; i<4; i++){
            nrOfServers[i-2] = in[i];
        }
        ByteBuffer.wrap(nrOfServers);


        byte [] adress= new byte [4];



        //sequenceBuilder.append((byte)0);
        //bytes= sequenceBuilder.toByteArray();

        //sequenceBuilder.appendShort(nrOfServers);




    }


}
