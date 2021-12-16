package com.wangwenjun.mockito.lesson11;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class VerificationModeTest
{
    @Mock
    private List<String> list;

    @Test
    public void test1()
    {
        list.add("Mockito");


        verify(list).add("Mockito");
        verify(list, times(0)).size();
        verify(list, atMost(1)).add("Mockito");
        verify(list, never()).clear();

        list.add("Mockito");
        list.add("Mockito");
        verify(list, atLeastOnce()).add("Mockito");
        verify(list, atLeast(2)).add("Mockito");
        verify(list, atMost(3)).add("Mockito");
    }

    /**
     * This is used to verify that only one method is called on a mock.
     * It fails if any other method is called on the mock object.
     * <p>
     * only one method exactly(if call other mock object method will be verify failed.)
     * <p>
     * only one method and call once
     */
    @Test
    public void test2()
    {
        list.add("Mockito");
//        list.add("Mockito");
        //list.clear();//failed
        verify(list, only()).add("Mockito");
    }

    @Test
    public void test3()
    {
        verifyZeroInteractions(list);
    }

    @Test
    public void test4()
    {
        list.add("Mockito");
        verify(list).add("Mockito");
        list.clear();
        verify(list).clear();
        list.size();

        verify(list).size();
        verifyNoMoreInteractions(list);
    }
}
