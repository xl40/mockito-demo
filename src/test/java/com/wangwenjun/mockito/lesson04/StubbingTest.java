package com.wangwenjun.mockito.lesson04;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class StubbingTest {

    private List<String> list;

    @Before
    public void init() {
        this.list = mock(ArrayList.class);
    }

    @Test
    public void howToUseStubbing() {
        when(list.get(0)).thenReturn("first");
        assertThat(list.get(0), equalTo("first"));

        when(list.get(anyInt())).thenThrow(new RuntimeException());
        try {
            list.get(0);
            fail();
        } catch (Exception e) {
            assertThat(e, instanceOf(RuntimeException.class));
        }
    }

    @Test
    public void howToStubbingVoidMethod() {
        doNothing().when(list).clear();
        list.clear();
        verify(list, times(1)).clear();

        doThrow(RuntimeException.class).when(list).clear();

        try {
            list.clear();
            fail();
        } catch (Exception e) {
            assertThat(e, instanceOf(RuntimeException.class));
        }
    }

    @Test
    public void stubbingDoReturn() {
        when(list.get(0)).thenReturn("first");
        doReturn("second").when(list).get(1);
        assertThat(list.get(0), equalTo("first"));
        assertThat(list.get(1), equalTo("second"));
    }

    @Test
    public void iterateSubbing() {
        when(list.size()).thenReturn(1).thenReturn(2).thenReturn(3).thenReturn(4);

        assertThat(list.size(), equalTo(1));
        assertThat(list.size(), equalTo(2));
        assertThat(list.size(), equalTo(3));
        assertThat(list.size(), equalTo(4));
        assertThat(list.size(), equalTo(4));
    }

    @Test
    public void stubbingWithAnswer() {
        when(list.get(anyInt())).thenAnswer(invocationOnMock ->
        {
            Integer index = invocationOnMock.getArgumentAt(0, Integer.class);
            return String.valueOf(index * 10);
        });

        assertThat(list.get(0), equalTo("0"));
        assertThat(list.get(999), equalTo("9990"));
    }


    @Test
    public void stubbingWithRealCall() {
        StubbingService service = mock(StubbingService.class);
        when(service.getS()).thenReturn("Alex");
        assertThat(service.getS(), equalTo("Alex"));

        when(service.getI()).thenCallRealMethod();
        assertThat(service.getI(), equalTo(10));
    }

    @After
    public void destroy() {
        reset(this.list);
    }

}
