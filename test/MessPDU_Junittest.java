package test;

import Pdu.PduMess;
import org.junit.Assert;
import org.junit.Test;

import java.io.UnsupportedEncodingException;

/**
 * Created by viyanateaa on 2016-11-30.
 */
public class MessPDU_Junittest {

    @Test
    public void sendmesstest() throws UnsupportedEncodingException {
        byte[] bA = {10,0,4,0,0,3,0,0,0,0,0,0,72,69,74,0,75,76,65,83};
        PduMess pM = new PduMess("KLAS", "HEJ");

        for(int i=0;i<pM.getBytes().length;i++)
            System.out.print(pM.getBytes()[i] +" ");

        for(int i=0;i<bA.length;i++)
            Assert.assertEquals(bA[i],pM.getBytes()[i]);

    }
}
