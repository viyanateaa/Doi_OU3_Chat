package test;



import Pdu.PduParticipants;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * Created by viyanateaa on 2017-01-02.
 */
public class ParticipantsPDU_JunitTest {
    @Test
    public void ShouldBeAbleToReadParticipants() throws IOException {

        byte [] list = {(byte)19,2,0,9,'P','O',0,'J','O','N','N','Y',0,0,0,0};
        InputStream In_steam = new ByteArrayInputStream(list,0,list.length);

        In_steam.read();

        PduParticipants participants_fromStream = new PduParticipants(In_steam);
        byte [] list_2 = participants_fromStream.getBytes();

        assertEquals(list_2.length, list.length);
    }

    @Test
    public void ShouldBeAbleToReadParticipantsClient() throws IOException {

        byte [] list = {(byte)19,2,0,9,'P','O',0,'J','O','N','N','Y',0,0,0,0};
        InputStream In_steam = new ByteArrayInputStream(list,0,list.length);
        In_steam.read();

        PduParticipants participants_fromStream = new PduParticipants(In_steam);
        //byte [] list_2 = participants_fromStream.getBytes();
        System.out.println(participants_fromStream.getParticipantsList()[0]);

        //String Text = new String ("PO");



        assertEquals(participants_fromStream.getParticipantsList()[0], "PO");
    }




}
