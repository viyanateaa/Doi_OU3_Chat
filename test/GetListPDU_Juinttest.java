package test;

import Pdu.GetList;
import org.junit.Test;

import java.io.UnsupportedEncodingException;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * Course: Datakommutikation och internet 5DV167
 * Assignment: OU3
 * Written by: Kristoffer & Viyan
 * Version: 19/1 -17.
 */
public class GetListPDU_Juinttest {
    @Test
    public void ShouldBeAbleToCreateGetList() throws UnsupportedEncodingException {
        GetList getList = new GetList();
    }


    @Test
    public void ShouldHaveCorrectOp (){
        GetList getList = new GetList();
        byte[] numbrOfOp = getList.getBytes();
        assertEquals((numbrOfOp[0]), 3);
    }


    @Test
    public void shouldGetWrongOpNumber() throws Exception {
        GetList getList = new GetList();
        byte[] list = getList.getBytes();
        assertNotEquals((list[1]), 3);
    }


    @Test
    public void shouldBeAbleToCreateGetListCorrect() throws Exception {
        GetList getList = new GetList();
        byte[] list = getList.getBytes();
        assertEquals((list[0]), 3);
        for (int i = 1; i <4 ; i++) {
            assertEquals(list[i], 0);
        }

    }

    @Test
    public void shouldBeAbleToGetWrongList() throws Exception {
        GetList getList = new GetList();
        byte[] list = getList.getBytes();
        assertEquals((list[0]), 3);
        for (int i = 1; i <4 ; i++) {
            assertNotEquals(list[i], 5);
        }

    }
}
