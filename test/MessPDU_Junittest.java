package test;

import Pdu.Checksum;
import Pdu.PduMess;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertNotEquals;
//comment%%
/**
 * Created by viyanateaa on 2016-11-30.
 */
public class MessPDU_Junittest {

    @Test
    public void shouldBeAbleToCreateMess() throws UnsupportedEncodingException {
        PduMess pM = new PduMess("KLAS", "HEJ");
    }

    @Test
    public void shouldBeAbleToCreateMessWithRightOp() throws UnsupportedEncodingException {

        PduMess pM = new PduMess("KLAS", "HEJ");
        byte [] mess_test = pM.getBytes();
        assertEquals(mess_test[0],10);
    }

    @Test
    public void shouldBeAbleToCreateMessPad() throws UnsupportedEncodingException {

        PduMess pM = new PduMess("KLAS", "HEJ");
        byte [] mess_test = pM.getBytes();
        assertEquals(mess_test[1],0);
        assertNotEquals(mess_test[1],null);
    }

    @Test
    public void shouldBeAbleToCreateMessIdLength() throws UnsupportedEncodingException {
        PduMess pM = new PduMess("KLAS", "HEJ");
        byte [] mess_test = pM.getBytes();

       // System.out.println(mess_test);
        assertEquals(mess_test[2],4);
        assertNotEquals(mess_test[2],null);
    }
    @Test
    public void shouldBeAbleToCreateMessCheckSum() throws UnsupportedEncodingException {
        PduMess pM = new PduMess("KLAS", "HEJ");
        byte [] mess_test = pM.getBytes();

        // System.out.println(mess_test);
        assertEquals(0,new Checksum().computeChecksum(mess_test));
        assertNotEquals(null,new Checksum().computeChecksum(mess_test));
    }
    @Test
    public void shouldBeAbleToCreateMesslength() throws UnsupportedEncodingException {
        PduMess pM = new PduMess("KLAS", "HEJ");
        byte [] mess_test = pM.getBytes();
        //System.out.println(mess_test[5]);

       assertEquals(mess_test[5],3);
       assertNotEquals(mess_test[5],null);
    }

    @Test
    public void shouldBeAbleToCreatePadAfterMessCorrectPad() throws UnsupportedEncodingException {
        PduMess pM = new PduMess("KLAS", "HEJ");
        byte [] mess_test = pM.getBytes();


       for(int i = 6 ;i < 11 ;i++){
            assertEquals(mess_test[i],0);
            assertNotEquals(mess_test[4],null);
        }
    }


    @Test
    public void shouldBeAbleToCreateMess1() throws UnsupportedEncodingException {
        PduMess pM = new PduMess("KLAS", "HEJ");
        byte [] mess_test = pM.getBytes();

        byte[] RightMess = {mess_test[12], mess_test[13], mess_test[14]};

        assertEquals(new String(RightMess, "UTF-8"),"HEJ");
        assertNotEquals(new String(RightMess, "UTF-8"),"hej");
        assertNotEquals(RightMess,null);
    }


    @Test
    public void shouldBeAbleToCreateMessClientId() throws UnsupportedEncodingException {
        PduMess pM = new PduMess("KLAS", "HEJ");
        byte [] mess_test = pM.getBytes();

        byte[] RightID = {mess_test[16], mess_test[17], mess_test[18],mess_test[19]};
       // System.out.println(new String(RightID, "UTF-8"));

        assertEquals(new String(RightID, "UTF-8"),"KLAS");
        assertNotEquals(new String(RightID, "UTF-8"),"hej");
        assertNotEquals(RightID,null);
    }



    @Test
    public void shouldBeAbleToReadMessFromStream() throws IOException {

        PduMess pM = new PduMess("Jonny", "HEJ!");
        byte [] mess_test_1 = pM.getBytes();

        InputStream In_steam = new ByteArrayInputStream(mess_test_1,0,mess_test_1.length);
        In_steam.read();

        PduMess mess_fromStream = new PduMess(In_steam);
        byte[] mess_test_2 = mess_fromStream.getBytes();

        assertEquals(mess_test_2.length,mess_test_1.length);
        assertNotEquals(mess_fromStream,null);
    }

    @Test
    public void shouldBeAbleToReadMessFromStreamCheckIdlength() throws IOException {

        PduMess pM = new PduMess("Jonny", "HEJ!");
        byte [] mess_test_1 = pM.getBytes();

        InputStream In_steam = new ByteArrayInputStream(mess_test_1,0,mess_test_1.length);
        In_steam.read();

        PduMess mess_fromStream = new PduMess(In_steam);
        byte[] mess_test_2 = mess_fromStream.getBytes();

        assertEquals(mess_test_2[2],5);
        assertNotEquals(mess_test_2[2],null);
    }
    @Test
    public void shouldBeAbleToReadMessFromStreamChecksum() throws IOException {

        PduMess pM = new PduMess("Jonny", "HEJ!");
        byte [] mess_test_1 = pM.getBytes();

        InputStream In_steam = new ByteArrayInputStream(mess_test_1,0,mess_test_1.length);
        In_steam.read();

        PduMess mess_fromStream = new PduMess(In_steam);
        byte[] mess_test_2 = mess_fromStream.getBytes();

        assertEquals(0,new Checksum().computeChecksum(mess_test_2));
        assertNotEquals(null,new Checksum().computeChecksum(mess_test_2));
    }

    @Test
    public void shouldBeAbleToReadMessFromStreamMessLength() throws IOException {

        PduMess pM = new PduMess("Jonny", "HEJ!");
        byte [] mess_test_1 = pM.getBytes();

        InputStream In_steam = new ByteArrayInputStream(mess_test_1,0,mess_test_1.length);
        In_steam.read();

        PduMess mess_fromStream = new PduMess(In_steam);
        byte[] mess_test_2 = mess_fromStream.getBytes();

        //System.out.println(mess_test_2[5]);

        assertEquals(mess_test_2[5],4);
        assertNotEquals(mess_test_2[5],null);
    }

    @Test
    public void shouldBeAbleToReadMessFromStreamWithCorrectPad() throws IOException {

        PduMess pM = new PduMess("Jonny", "HEJ!");
        byte [] mess_test_1 = pM.getBytes();

        InputStream In_steam = new ByteArrayInputStream(mess_test_1,0,mess_test_1.length);
        In_steam.read();

        PduMess mess_fromStream = new PduMess(In_steam);
        byte[] mess_test_2 = mess_fromStream.getBytes();

        //System.out.println(mess_test_2[5]);

        for(int i = 6 ;i < 11 ;i++){
            assertEquals(mess_test_2[i],0);
            assertNotEquals(mess_test_2[4],null);
        }
    }


    @Test
    public void shouldBeAbleToReadMessFromStreamGetCorrectMess() throws IOException {

        PduMess pM = new PduMess("Jonny", "HEJ!");
        byte [] mess_test_1 = pM.getBytes();
        InputStream In_steam = new ByteArrayInputStream(mess_test_1,0,mess_test_1.length);
        In_steam.read();
        PduMess mess_fromStream = new PduMess(In_steam);
        byte[] mess_test_2 = mess_fromStream.getBytes();
        byte[] RightMess = {mess_test_2[12], mess_test_2[13], mess_test_2[14],mess_test_2[15]};

        assertEquals(new String(RightMess, "UTF-8"),"HEJ!");
        assertNotEquals(new String(RightMess, "UTF-8"),"hej");
        assertNotEquals(RightMess,null);
    }

    @Test
    public void shouldBeAbleToReadMessFromStreamGetCorrectClientId() throws IOException {

        PduMess pM = new PduMess("Jonny", "HEJ!");
        byte [] mess_test_1 = pM.getBytes();
        InputStream In_steam = new ByteArrayInputStream(mess_test_1,0,mess_test_1.length);
        In_steam.read();
        PduMess mess_fromStream = new PduMess(In_steam);
        byte[] mess_test_2 = mess_fromStream.getBytes();

        byte[] RightID = {mess_test_2[16], mess_test_2[17], mess_test_2[18],mess_test_2[19],mess_test_2[20]};
        // System.out.println(new String(RightID, "UTF-8"));

        assertEquals(new String(RightID, "UTF-8"),"Jonny");
        assertNotEquals(new String(RightID, "UTF-8"),"hej");
        assertNotEquals(RightID,null);
    }
}




