package test;

import Pdu.PduSlist;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * Course: Datakommutikation och internet 5DV167
 * Assignment: OU3
 * Written by: Kristoffer & Viyan
 * Version: 19/1 -17.
 */

public class SlistPdu_JunitTest {
    @Test
    public void shouldBeAbleToReadPduSlist() throws IOException {


        byte [] slist = {(byte)4,0,0,(byte)2,(byte)127,0,0,1,0,20,1,2,'S','N',0,0,127,0,0,1,0,20,1,2,'S','L',0,0};
        InputStream In_steam = new ByteArrayInputStream(slist,0,slist.length);
        In_steam.read();
        PduSlist slist_fromStream = new PduSlist(In_steam);
        slist_fromStream.printInfo();
        assertNotEquals(slist_fromStream, null);
    }

    @Test
    public void shouldBeAbleToReadPduSlist_1() throws IOException {


        byte [] slist = {(byte)4,0,0,(byte)2,(byte)127,0,0,1,0,20,1,2,'S','N',0,0,127,0,0,1,0,20,1,2,'S','L',0,0};
        InputStream In_steam = new ByteArrayInputStream(slist,0,slist.length);
        In_steam.read();

        PduSlist slist_fromStream = new PduSlist(In_steam);
        byte [] list_Test = slist_fromStream.getBytes();

        //System.out.println(slist_fromStream.getSequenceBuilder().toString());

        assertEquals(list_Test.length, slist.length);
    }
    @Test
    public void shouldBeAbleToReadPduSlistNumberOfServer() throws IOException {


        byte [] slist = {(byte)4,0,0,(byte)2,(byte)127,0,0,1,0,20,1,2,'S','N',0,0,127,0,0,1,0,20,1,2,'S','L',0,0};
        InputStream In_steam = new ByteArrayInputStream(slist,0,slist.length);
        In_steam.read();

        PduSlist slist_fromStream = new PduSlist(In_steam);
        byte [] list_Test = slist_fromStream.getBytes();

        //System.out.println(slist_fromStream.getSequenceBuilder().toString());

        assertEquals(list_Test [3], 2);
    }
    @Test
    public void shouldBeAbleToReadPduSlistAdress() throws IOException {


        byte [] slist = {(byte)4,0,0,(byte)2,(byte)127,0,0,1,0,20,1,2,'S','N',0,0,127,0,0,1,0,20,1,2,'S','L',0,0};
        InputStream In_steam = new ByteArrayInputStream(slist,0,slist.length);
        In_steam.read();

        PduSlist slist_fromStream = new PduSlist(In_steam);
        byte [] list_Test = slist_fromStream.getBytes();

        //System.out.println(slist_fromStream.getSequenceBuilder().toString());

        assertEquals(list_Test [4], 127);
    }

    @Test
    public void shouldBeAbleToReadPduSlistAdress1() throws IOException {


        byte [] slist = {(byte)4,0,0,(byte)2,(byte)127,0,0,1,0,20,1,2,'S','N',0,0,127,0,0,1,0,20,1,2,'S','L',0,0};
        InputStream In_steam = new ByteArrayInputStream(slist,0,slist.length);
        In_steam.read();

        PduSlist slist_fromStream = new PduSlist(In_steam);
        byte [] list_Test = slist_fromStream.getBytes();

        //System.out.println(slist_fromStream.getSequenceBuilder().toString());

        assertNotEquals(list_Test [4], 126);
    }
    @Test
    public void shouldBeAbleToReadPduSlistPort() throws IOException {


        byte [] slist = {(byte)4,0,0,(byte)2,(byte)127,0,0,1,0,20,1,2,'S','N',0,0,127,0,0,1,0,20,1,2,'S','L',0,0};
        InputStream In_steam = new ByteArrayInputStream(slist,0,slist.length);
        In_steam.read();

        PduSlist slist_fromStream = new PduSlist(In_steam);
        byte [] list_Test = slist_fromStream.getBytes();

        //System.out.println(slist_fromStream.getSequenceBuilder().toString());

        assertEquals(list_Test [9], 20);
    }
    @Test
    public void shouldBeAbleToReadPduSlistPort1() throws IOException {


        byte [] slist = {(byte)4,0,0,(byte)2,(byte)127,0,0,1,0,20,1,2,'S','N',0,0,127,0,0,1,0,20,1,2,'S','L',0,0};
        InputStream In_steam = new ByteArrayInputStream(slist,0,slist.length);
        In_steam.read();

        PduSlist slist_fromStream = new PduSlist(In_steam);
        byte [] list_Test = slist_fromStream.getBytes();

        //System.out.println(slist_fromStream.getSequenceBuilder().toString());

        assertNotEquals(list_Test [8], 20);
    }
    @Test
    public void shouldBeAbleToReadPduSlistNumberOfClient() throws IOException {


        byte [] slist = {(byte)4,0,0,(byte)2,(byte)127,0,0,1,0,20,1,2,'S','N',0,0,127,0,0,1,0,20,1,2,'S','L',0,0};
        InputStream In_steam = new ByteArrayInputStream(slist,0,slist.length);
        In_steam.read();

        PduSlist slist_fromStream = new PduSlist(In_steam);
        byte [] list_Test = slist_fromStream.getBytes();

        //System.out.println(slist_fromStream.getSequenceBuilder().toString());

        assertNotEquals(list_Test [10], 2);
    }
    @Test
    public void shouldBeAbleToReadPduSlistNumberOfClient1() throws IOException {


        byte [] slist = {(byte)4,0,0,(byte)2,(byte)127,0,0,1,0,20,1,2,'S','N',0,0,127,0,0,1,0,20,1,2,'S','L',0,0};
        InputStream In_steam = new ByteArrayInputStream(slist,0,slist.length);
        In_steam.read();

        PduSlist slist_fromStream = new PduSlist(In_steam);
        byte [] list_Test = slist_fromStream.getBytes();

        //System.out.println(slist_fromStream.getSequenceBuilder().toString());

        assertEquals(list_Test [10], 1);
    }
    @Test
    public void shouldBeAbleToReadPduSlistNumberOfServerllength() throws IOException {


        byte [] slist = {(byte)4,0,0,(byte)2,(byte)127,0,0,1,0,20,1,2,'S','N',0,0,127,0,0,1,0,20,1,2,'S','L',0,0};
        InputStream In_steam = new ByteArrayInputStream(slist,0,slist.length);
        In_steam.read();

        PduSlist slist_fromStream = new PduSlist(In_steam);
        byte [] list_Test = slist_fromStream.getBytes();

        //System.out.println(slist_fromStream.getSequenceBuilder().toString());

        assertEquals(list_Test [11], 2);
    }
    @Test
    public void shouldBeAbleToReadPduSlistNumberOfServerllength1() throws IOException {


        byte [] slist = {(byte)4,0,0,(byte)2,(byte)127,0,0,1,0,20,1,2,'S','N',0,0,127,0,0,1,0,20,1,2,'S','L',0,0};
        InputStream In_steam = new ByteArrayInputStream(slist,0,slist.length);
        In_steam.read();

        PduSlist slist_fromStream = new PduSlist(In_steam);
        byte [] list_Test = slist_fromStream.getBytes();

        //System.out.println(slist_fromStream.getSequenceBuilder().toString());

        assertNotEquals(list_Test [11], null);
    }
    @Test
    public void shouldBeAbleToReadPduSlistNumberOfServerllength3() throws IOException {


        byte [] slist = {(byte)4,0,0,(byte)2,(byte)127,0,0,1,0,20,1,2,'S','N',0,0,127,0,0,1,0,20,1,2,'S','L',0,0};
        InputStream In_steam = new ByteArrayInputStream(slist,0,slist.length);
        In_steam.read();

        PduSlist slist_fromStream = new PduSlist(In_steam);
        byte [] list_Test = slist_fromStream.getBytes();

        //System.out.println(slist_fromStream.getSequenceBuilder().toString());

        assertNotEquals(list_Test [11], 5);
    }

}
