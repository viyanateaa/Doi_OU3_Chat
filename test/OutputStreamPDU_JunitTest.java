package test;

import Pdu.PduOutputStream;
import Pdu.PduMess;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * Course: Datakommutikation och internet 5DV167
 * Assignment: OU3
 * Written by: Kristoffer & Viyan
 * Version: 19/1 -17.
 */
public class OutputStreamPDU_JunitTest {

    @Test
    public void shouldBeAbleToCreateOutPut() throws IOException {

        //Mess
        PduMess pM = new PduMess("Jonny", "HEJ! Hur är det?");
        byte[] mess_test_1 = pM.getBytes();

        ByteArrayOutputStream test_pdu_out = new ByteArrayOutputStream(36);
        OutputStream out = test_pdu_out;
        PduOutputStream pdu_out = new PduOutputStream(out);

        pdu_out.sendPDU(pM);

        byte[] pduM = test_pdu_out.toByteArray();

        for (int i =0; i<mess_test_1.length;i++){
            assertEquals(mess_test_1[i], pduM[i]);
            assertEquals(mess_test_1.length, pduM.length);
        }

        assertNotEquals(null, pduM);



    }

}
