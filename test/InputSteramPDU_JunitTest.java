package test;

import Pdu.PDUInputStream;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

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
    public void shouldBeAbleToReadInput() throws IOException {

       /* //Pjoin
        byte [] list = {(byte)16,(byte)6,(byte)0,(byte)0,0,0,0,0,'J','o','n','n','y','!',0,0,0};
        InputStream In = new ByteArrayInputStream(list,0,list.length);
        PDUInputStream InStream = new PDUInputStream(In);


        assertNotEquals(In_steam,null);*/
    }
}

