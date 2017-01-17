package test;

import Pdu.PDUInputStream;
import Pdu.Pdu;
import Pdu.PduCorrupt;
import Pdu.PduMess;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * Created by viyanateaa on 2017-01-03.
 */
public class InputSteramPDU_JunitTest {
    @Test
    public void shouldBeAbleToCreateInput() throws IOException {

        //Pjoin
        byte [] list = {(byte)16,(byte)6,(byte)0,(byte)0,0,0,0,0,'J','o','n','n','y','!',0,0,0};
        InputStream In_steam = new ByteArrayInputStream(list,0,list.length);
        new PDUInputStream(In_steam);

    }

    @Test
    public void shouldBeAbleToFindFalseInput() throws IOException {

        //Pjoin
        byte [] list = {-2,-3,-0,-5};
        InputStream In_steam = new ByteArrayInputStream(list,0,list.length);
        PDUInputStream in_list =new PDUInputStream(In_steam);
        Pdu pdu =in_list.readPdu();
        assertEquals(pdu.getClass(),PduCorrupt.class);

    }


    @Test
    public void shouldBeAbleToReadInputMess() throws IOException {

        //Mess
        PduMess pM = new PduMess("Jonny", "HEJ! Hur är det?");
        byte [] mess_test_1 = pM.getBytes();

        InputStream In_steam = new ByteArrayInputStream(mess_test_1,0,mess_test_1.length);
        PDUInputStream in_mess = new PDUInputStream(In_steam);
        Pdu pdu = in_mess.readPdu();


        for(int i = 0 ;i < pdu.getBytes().length ;i++){
            assertEquals(pM.getBytes()[i],pdu.getBytes()[i]);
        }

        for(int i = 0 ;i < pdu.getBytes().length ;i++){
            assertNotEquals(pdu.getBytes()[i],null);
        }

        pdu.printInfo();
    }


    @Test
    public void shouldBeAbleToReadInputMessIsNotNull() throws IOException {

        //Mess
        PduMess pM = new PduMess("Jonny", "HEJ! Hur är det?");
        byte[] mess_test_1 = pM.getBytes();

        InputStream In_steam = new ByteArrayInputStream(mess_test_1, 0, mess_test_1.length);
        PDUInputStream in_mess = new PDUInputStream(In_steam);
        Pdu pdu = in_mess.readPdu();


        for (int i = 0; i < pdu.getBytes().length; i++) {
            assertEquals(pM.getBytes()[i], pdu.getBytes()[i]);
        }

        assertNotEquals(null, pdu);
    }

    @Test
    public void shouldBeAbleToReadInputQuit() throws IOException {

        byte [] list = {(byte)11,0,0,0};
        InputStream In_steam = new ByteArrayInputStream(list,0,list.length);
        PDUInputStream in_quit = new PDUInputStream(In_steam);
        Pdu pdu = in_quit.readPdu();
        pdu.printInfo();
    }

    @Test
    public void shouldBeAbleToReadInputParticipants() throws IOException {

        byte [] list = {(byte)19,2,0,9,'P','O',0,'J','O','N','N','Y',0,0,0,0};
        InputStream In_steam = new ByteArrayInputStream(list,0,list.length);
        PDUInputStream in_participants = new PDUInputStream(In_steam);
        Pdu pdu = in_participants.readPdu();

        for (int i =1; i < list.length; i++) {

            assertEquals(pdu.getBytes()[1], list[1]);
        }



        pdu.printInfo();
    }



}

