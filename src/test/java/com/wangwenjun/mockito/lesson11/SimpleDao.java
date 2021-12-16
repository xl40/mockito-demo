package com.wangwenjun.mockito.lesson11;

public class SimpleDao
{
    public boolean findSimple(Simple simple)
    {
        throw new RuntimeException();
    }

    public boolean updateSimple(Simple simple)
    {
        throw new RuntimeException();
    }

    public boolean createSimple(Simple simple)
    {
        throw new RuntimeException();
    }
}
