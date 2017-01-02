package test;

import Pdu.PduPleave;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * Created by viyanateaa on 2017-01-02.
 */
public class Pleave_JunitTest {

    @Test
    public void shouldBeAbleToReadPduPleaveCorrect() throws IOException {

        byte [] list = {(byte)17,(byte)6,(byte)0,(byte)0,0,0,0,0,'J','o','n','n','y','!',0,0,0};
        InputStream In_steam = new ByteArrayInputStream(list,0,list.length);
        In_steam.read();
        PduPleave pleave_fromStream = new PduPleave (In_steam);
        assertEquals(pleave_fromStream.getClientIdentity(), "Jonny!");
    }
    @Test
    public void MakeSureClientIDIsNotNull() throws IOException {

        byte [] list = {(byte)17,(byte)6,(byte)0,(byte)0,0,0,0,0,'J','o','n','n','y','!',0,0,0};
        InputStream In_steam = new ByteArrayInputStream(list,0,list.length);
        In_steam.read();
        PduPleave pleave_fromStream = new PduPleave (In_steam);

        assertNotEquals(pleave_fromStream.getClientIdentity(), null);
    }

    @Test
    public void shouldBeAbleToReadPduPleaveCorrectTime() throws IOException {

        byte [] list = {(byte)17,(byte)6,(byte)0,(byte)0,0,0,0,0,'J','o','n','n','y','!',0,0,0};
        InputStream In_steam = new ByteArrayInputStream(list,0,list.length);
        In_steam.read();
        PduPleave pleave_fromStream = new PduPleave (In_steam);
        long Time = 0;
        Date test_time = new Date(Time);

        assertEquals(test_time,pleave_fromStream.getTimeStamp());

    }

    @Test
    public void makesureTimeIsNotNull() throws IOException {

        byte [] list = {(byte)17,(byte)6,(byte)0,(byte)0,0,0,0,0,'J','o','n','n','y','!',0,0,0};
        InputStream In_steam = new ByteArrayInputStream(list,0,list.length);
        In_steam.read();
        PduPleave pleave_fromStream = new PduPleave (In_steam);
        assertNotEquals(null,pleave_fromStream.getTimeStamp());

    }


}
