package com.wangwenjun.mockito.lesson10;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.hamcrest.Factory;

public class CompareNumberMatcher<T extends Number> extends BaseMatcher<T>
{
    private final T value;

    private final Compare<T> COMPARE;

    public CompareNumberMatcher(T value, boolean great)
    {
        this.COMPARE = new DefaultNumberCompare<>(great);
        this.value = value;
    }

    @Override
    public boolean matches(Object actual)
    {
        return this.COMPARE.compare(value, (T) actual);
    }

    @Factory
    public static <T extends Number> CompareNumberMatcher<T> gt(T value)
    {
        return new CompareNumberMatcher<>(value, true);
    }

    @Factory
    public static <T extends Number> CompareNumberMatcher<T> lt(T value)
    {
        return new CompareNumberMatcher<>(value, false);
    }

    private interface Compare<T extends Number>
    {
        boolean compare(T expected, T actual);
    }

    private static class DefaultNumberCompare<T extends Number> implements Compare<T>
    {

        private final boolean great;

        private DefaultNumberCompare(boolean great)
        {
            this.great = great;
        }

        @Override
        public boolean compare(T expected, T actual)
        {
            Class<?> clazz = actual.getClass();
            if (clazz == Integer.class)
            {
                return great ? (Integer) actual > (Integer) expected : (Integer) actual < (Integer) expected;
            } else if (clazz == Short.class)
            {
                return great ? (Short) actual > (Short) expected : (Short) actual < (Short) expected;
            } else if (clazz == Byte.class)
            {
                return great ? (Byte) actual > (Byte) expected : (Byte) actual < (Byte) expected;
            } else if (clazz == Double.class)
            {
                return great ? (Double) actual > (Double) expected : (Double) actual < (Double) expected;
            } else if (clazz == Float.class)
            {
                return great ? (Float) actual > (Float) expected : (Float) actual < (Float) expected;
            } else if (clazz == Long.class)
            {
                return great ? (Long) actual > (Long) expected : (Long) actual < (Long) expected;
            } else
            {
                throw new AssertionError("The number type " + clazz + "not supported.");
            }
        }
    }

    @Override
    public void describeTo(Description description)
    {
        description.appendText("compare two number failed.");
    }
}
