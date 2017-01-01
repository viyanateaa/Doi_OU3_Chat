package test;

import Pdu.PduJoin;
import org.junit.Test;

import java.io.UnsupportedEncodingException;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * Created by viyanateaa on 2016-12-30.
 */
public class JoinPDU_JunitTest {

    @Test
    public void ShouldBeAbleToCreatePduJion() throws UnsupportedEncodingException {
        PduJoin join = new PduJoin("Jon");
    }

    @Test
    public void shouldHaveCorrectOpNumberPos() throws Exception {
        PduJoin join = new PduJoin("Jon");
        byte[] join_list_1 = join.getBytes();
        assertEquals((join_list_1[0]), 12);
    }

    @Test
    public void shouldHaveWrongOpNumber() throws Exception {
        PduJoin join = new PduJoin("Jon");
        byte[] join_list_2 = join.getBytes();
        assertNotEquals((join_list_2[0]), 13);
    }

    @Test
    public void shouldNotHaveWrongOpNumberPos() throws Exception {
        PduJoin join = new PduJoin("Jon");
        byte[] join_list_3 = join.getBytes();
        assertNotEquals((join_list_3[1]), 12);
    }

    @Test
    public void ShouldBeAbleToCreatePduJoinWithRightIdNumb() throws UnsupportedEncodingException {
        PduJoin join = new PduJoin("Jonny!");
        byte[] join_list_4 = join.getBytes();

        assertEquals((join_list_4[0]), 12);
        assertEquals((join_list_4[1]), 6);

    }


    @Test
    public void ShouldBeAbleToCreatePduJionWithWrongIdNumb() throws UnsupportedEncodingException {
        PduJoin join = new PduJoin("Jonny!");
        byte[] join_list_5 = join.getBytes();

        assertEquals((join_list_5[0]), 12);
        assertNotEquals((join_list_5[1]), 5);

    }

    @Test
    public void ShouldBeAbleToCreatePduJionWithRightPad() throws UnsupportedEncodingException {
        PduJoin join = new PduJoin("Jonny!");
        byte[] join_list_6 = join.getBytes();

        assertEquals((join_list_6[0]), 12);
        assertEquals((join_list_6[1]), 6);
        for (int i = 2; i < 4; i++) {
            assertEquals((join_list_6[i]), 0);
        }

    }

    @Test
    public void ShouldBeAbleToCreatePduJionWithRightValueOfPad() throws UnsupportedEncodingException {
        PduJoin join = new PduJoin("Jonny!");
        byte[] join_list_7 = join.getBytes();

        assertEquals((join_list_7[0]), 12);
        assertEquals((join_list_7[1]), 6);
        for (int i = 2; i < 4; i++) {
            assertNotEquals((join_list_7[i]), 100100);
        }

    }


    @Test
    public void ShouldBeAbleToCreateCorrectPduJionWithRightID() throws UnsupportedEncodingException {
        PduJoin join = new PduJoin("Jonny!");
        byte[] join_list_8 = join.getBytes();

        assertEquals((join_list_8[0]), 12);
        assertEquals((join_list_8[1]), 6);

        for (int i = 2; i < 4; i++) {
            assertEquals((join_list_8[i]), 0);
        }


        byte[] RightId = {join_list_8[4], join_list_8[5], join_list_8[6], join_list_8[7], join_list_8[8], join_list_8[9]};

        assertEquals("Jonny!", new String(RightId, "UTF-8"));
        assertEquals(RightId.length, 6);


    }

    @Test
    public void MakeSureCreateRightPdu() throws Exception {
        PduJoin join = new PduJoin("Jonny!");
        byte[] join_list_9 = join.getBytes();
        assertEquals((join_list_9[0]), 12);
        assertEquals((join_list_9[1]), 6);

        for(int i = 2;i< 4;i++) {
            assertEquals((join_list_9[i]), 0);
        }

        byte[] RightId = {join_list_9[4], join_list_9[5], join_list_9[6], join_list_9[7], join_list_9[8], join_list_9[9]};

        assertEquals("Jonny!",new String(RightId,"UTF-8"));

        assertNotEquals(RightId.length,0);


        //    how pirnt jonny from RightID ??System.out.println();

    }
}