package test;

import Pdu.Checksum;
import org.junit.Test;

import java.io.UnsupportedEncodingException;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * Created by viyanateaa on 2017-01-03.
 */
public class CheckSum_Test {
    @Test
    public void ShouldBeAbleToCorrectSum() throws UnsupportedEncodingException {

        byte [] A = {(byte)172,50,87,0};
        A [3] = (new Checksum().computeChecksum(A));
        assertEquals(0,new Checksum().computeChecksum(A));
        assertNotEquals(null,new Checksum().computeChecksum(A));
    }
    @Test
    public void ShouldBeAbleToWrongSum() throws UnsupportedEncodingException {

        byte [] A = {(byte)172,50,87,5};
        A [3] = (new Checksum().computeChecksum(A));
        assertNotEquals(0,new Checksum().computeChecksum(A));
        //assertNotEquals(null,new Checksum().computeChecksum(A));
    }
}
