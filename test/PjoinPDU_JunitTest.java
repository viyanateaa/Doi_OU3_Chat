package test;

import Pdu.PduPjoin;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * Course: Datakommutikation och internet 5DV167
 * Assignment: OU3
 * Written by: Kristoffer & Viyan
 * Version: 19/1 -17.
 */
public class PjoinPDU_JunitTest {

    @Test
    public void shouldBeAbleToReadPduPjoin() throws IOException {


        byte [] list = {(byte)16,(byte)6,(byte)0,(byte)0,0,0,0,0,'J','o','n','n','y','!',0,0,0};
        InputStream In_steam = new ByteArrayInputStream(list,0,list.length);

        In_steam.read();

        PduPjoin pjoin_fromStream = new PduPjoin(In_steam);

        assertEquals(pjoin_fromStream.getClientIdentity(), "Jonny!");
        System.out.println(pjoin_fromStream.getTimeStamp());

    }

    @Test
    public void shouldBeAbleToReadPduPjoinCorrect() throws IOException {


        byte [] list = {(byte)16,(byte)6,(byte)0,(byte)0,0,0,0,0,'J','o','n','n','y','!',0,0,0};
        InputStream In_steam = new ByteArrayInputStream(list,0,list.length);

        In_steam.read();

        PduPjoin pjoin_fromStream = new PduPjoin(In_steam);

        assertNotEquals(pjoin_fromStream.getClientIdentity(), "Jony!");
       // System.out.println(pjoin_fromStream.getTimeStamp());

    }
    @Test
    public void shouldBeAbleToReadPduPjoinIsNotNull() throws IOException {


        byte [] list = {(byte)16,(byte)6,(byte)0,(byte)0,0,0,0,0,'J','o','n','n','y','!',0,0,0};
        InputStream In_steam = new ByteArrayInputStream(list,0,list.length);

        In_steam.read();

        PduPjoin pjoin_fromStream = new PduPjoin(In_steam);

        assertNotEquals(pjoin_fromStream.getClientIdentity(), null);
        // System.out.println(pjoin_fromStream.getTimeStamp());

        long Time = 0;
        Date test_time = new Date(Time);
        assertEquals(test_time,pjoin_fromStream.getTimeStamp());

    }
}
