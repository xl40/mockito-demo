package com.wangwenjun.mockito.lesson15;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Map;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class OneLinerStubbingTest
{

    //1.9.0
    @Test
    public void mockThenStubbingAndCall()
    {
        Map<String, String> map = mock(Map.class);
        when(map.get(anyString())).thenReturn("Default1", "Default2");
        assertThat(map.get("Hello"), equalTo("Default1"));
        assertThat(map.get("Hello"), equalTo("Default2"));
        assertThat(map.get("Hello"), equalTo("Default2"));
    }

    @Test
    public void oneLinerStubbing()
    {
        Map<String, String> map = when(mock(Map.class).get(anyString()))
                .thenReturn("Default1", "Default2")
                .getMock();

        assertThat(map.get("Hello"), equalTo("Default1"));
        assertThat(map.get("Hello"), equalTo("Default2"));
        assertThat(map.get("Hello"), equalTo("Default2"));
    }
}
