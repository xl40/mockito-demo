package com.wangwenjun.mockito.lesson15;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.anyInt;

@RunWith(MockitoJUnitRunner.class)
public class BddStyleTest
{
    @Mock
    private List<String> list;

    @Test
    public void testBdd()
    {
        given(list.get(anyInt())).willReturn("Alex");

        assertThat(list.get(10), equalTo("Alex"));
    }

}
