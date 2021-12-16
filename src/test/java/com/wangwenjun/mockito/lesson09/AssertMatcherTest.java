package com.wangwenjun.mockito.lesson09;

import org.junit.Test;

import java.util.stream.Stream;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

public class AssertMatcherTest
{

    @Test
    public void test()
    {
        int i = 10;
        assertThat(i, equalTo(10));
        assertThat(i, not(equalTo(20)));

        assertThat(i, is(10));

        assertThat(i, is(not(20)));


    }

    @Test
    public void test2()
    {
        double price = 23.45;
        assertThat(price, either(equalTo(23.45)).or(equalTo(23.54)));
        assertThat(price, both(equalTo(23.45)).and(not(equalTo(23.54))));

        assertThat(price, anyOf(is(23.45), is(43.34), is(53.24), not(35.24)));

        assertThat(price, allOf(is(23.45), not(is(43.34)), not(is(53.24)), not(35.24)));


        assertThat(Stream.of(1, 2, 3).anyMatch(i -> i > 2), equalTo(true));
        assertThat(Stream.of(1, 2, 3).allMatch(i -> i > 0), equalTo(true));
    }

    @Test
    public void test3()
    {
        double price = 23.45;
        assertThat("the double value assertion failed.", price, either(equalTo(23.65)).or(equalTo(23.54)));
    }
}
