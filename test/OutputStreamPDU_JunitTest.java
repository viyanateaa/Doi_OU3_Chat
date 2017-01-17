package test;

import Pdu.PDUOutputStream;
import Pdu.PduMess;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * Created by viyanateaa on 2017-01-17.
 */
public class OutputStreamPDU_JunitTest {

    @Test
    public void shouldBeAbleToCreateOutPut() throws IOException {

        //Mess
        PduMess pM = new PduMess("Jonny", "HEJ! Hur är det?");
        byte[] mess_test_1 = pM.getBytes();

        ByteArrayOutputStream test_pdu_out = new ByteArrayOutputStream(36);
        OutputStream out = test_pdu_out;
        PDUOutputStream pdu_out = new PDUOutputStream(out);

        pdu_out.sendPDU(pM);

        byte[] pduM = test_pdu_out.toByteArray();

        for (int i =0; i<mess_test_1.length;i++){
            assertEquals(mess_test_1[i], pduM[i]);
            assertEquals(mess_test_1.length, pduM.length);
        }

        assertNotEquals(null, pduM);



    }

}
