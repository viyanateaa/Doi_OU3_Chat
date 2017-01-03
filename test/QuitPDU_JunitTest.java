package test;

import Pdu.PduQuit;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * Created by viyanateaa on 2017-01-01.
 */
public class QuitPDU_JunitTest {


    @Test
    public void shouldBeAbleToCreatePduQuit() throws IOException {
        PduQuit quit_list = new PduQuit();
    }

    @Test
    public void shouldBeAbleToCreatePduQuitWithRightOpNumbr() throws Exception {
        PduQuit quit_list_1 = new PduQuit();
        byte[] numbrOfOp = quit_list_1.getBytes();
        assertEquals((numbrOfOp[0]), 11);

    }

    @Test
    public void shouldcheckRightOpNumbrPos() throws Exception {
        PduQuit quit_list_2 = new PduQuit();
        byte[] numbrOfOp = quit_list_2.getBytes();
        assertNotEquals((numbrOfOp[1]), 11);

    }

    @Test
    public void shouldcheckRightOpNumbr () throws Exception {
        PduQuit quit_list_3 = new PduQuit();
        byte[] numbrOfOp = quit_list_3.getBytes();
        assertNotEquals((numbrOfOp[0]), 12);

    }

    @Test
    public void shouldcheckRightOpNumbrValue () throws Exception {
        PduQuit quit_list_4 = new PduQuit();
        byte[] numbrOfOp = quit_list_4.getBytes();
        assertNotEquals((numbrOfOp[0]), null);

    }

    @Test
    public void shouldcheckCorrectValuePad () throws Exception {
        PduQuit quit_list_5 = new PduQuit();
        byte[] numbrOfOp = quit_list_5.getBytes();
        for(int i= 1; i < numbrOfOp.length;i++ ){
            assertEquals(numbrOfOp[i],0);
        }
    }


    @Test
    public void shouldcheckPadValueIsNotNull () throws Exception {
        PduQuit quit_list_5 = new PduQuit();
        byte[] numbrOfOp = quit_list_5.getBytes();
        for(int i= 1; i < numbrOfOp.length;i++ ){
            assertNotEquals(numbrOfOp[i],null);
        }
    }

    @Test
    public void shouldBeAbleToReadQuitFromInStream () throws Exception {
        byte [] list = {(byte)11,0,0,0};
        InputStream In_steam = new ByteArrayInputStream(list,0,list.length);

        In_steam.read();

        PduQuit quit_fromStream = new PduQuit(In_steam);
        byte [] list_2 = quit_fromStream.getBytes();

        assertEquals(list_2.length, list.length);
    }

    @Test
    public void shouldBeAbleToReadQuitFromInStreamPos () throws Exception {
        byte [] list = {(byte)11,0,0,0};
        InputStream In_steam = new ByteArrayInputStream(list,0,list.length);

        In_steam.read();

        PduQuit quit_fromStream = new PduQuit(In_steam);
        byte [] list_2 = quit_fromStream.getBytes();

        assertEquals(list_2 [1], 0);
        assertEquals(list_2 [2], 0);
        assertEquals(list_2 [3], 0);
    }

    @Test
    public void shouldBeAbleToReadQuitFromInStreamPosIsNotNull () throws Exception {
        byte [] list = {(byte)11,0,0,0};
        InputStream In_steam = new ByteArrayInputStream(list,0,list.length);

        In_steam.read();

        PduQuit quit_fromStream = new PduQuit(In_steam);
        byte [] list_2 = quit_fromStream.getBytes();

        assertNotEquals(list_2 [1], null);
        assertNotEquals(list_2 [2], null);
        assertNotEquals(list_2 [3], null);
    }




}
