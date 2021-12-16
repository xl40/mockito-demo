package com.wangwenjun.mockito.lesson08;


import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.anyCollection;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class WildcardArgumentMatcherTest
{

    @Mock
    private SimpleService simpleService;

    @Test
    public void wildcardMethod1()
    {
        when(simpleService.method1(anyInt(), anyString(), anyCollection(), isA(Serializable.class))).thenReturn(100);
        int result = simpleService.method1(1, "Alex", Collections.emptyList(), "Mockito");
        assertThat(result, equalTo(100));

        result = simpleService.method1(1, "Wang", Collections.emptySet(), "MockitoForJava");
        assertThat(result, equalTo(100));
    }

    @Test
    public void wildcardMethod1WithSpec()
    {
        when(simpleService.method1(anyInt(), anyString(), anyCollection(), isA(Serializable.class))).thenReturn(-1);
        when(simpleService.method1(anyInt(), eq("Alex"), anyCollection(), isA(Serializable.class))).thenReturn(100);
        when(simpleService.method1(anyInt(), eq("Wang"), anyCollection(), isA(Serializable.class))).thenReturn(200);


        int result = simpleService.method1(1, "Alex", Collections.emptyList(), "Mockito");
        assertThat(result, equalTo(100));

        result = simpleService.method1(1, "Wang", Collections.emptyList(), "Mockito");
        assertThat(result, equalTo(200));

        result = simpleService.method1(1, "sfsfs", Collections.emptyList(), "Mockito");
        assertThat(result, equalTo(-1));
    }


    @Test
    public void wildcardMethod2()
    {
        List<Object> emptyList = Collections.emptyList();
        doNothing().when(simpleService).method2(anyInt(), anyString(), anyCollection(), isA(Serializable.class));
        simpleService.method2(1, "Alex", emptyList, "Mockito");
        verify(simpleService, times(1)).method2(1, "Alex", emptyList, "Mockito");
        verify(simpleService, times(1)).method2(anyInt(), eq("Alex"), anyCollection(), isA(Serializable.class));

    }

    @After
    public void destroy()
    {
        reset(simpleService);
    }
}
