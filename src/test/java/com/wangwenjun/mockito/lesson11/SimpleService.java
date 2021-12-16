package com.wangwenjun.mockito.lesson11;

public class SimpleService
{

    private final SimpleDao simpleDao;

    public SimpleService(SimpleDao simpleDao)
    {
        this.simpleDao = simpleDao;
    }

    public boolean merge(Simple simple)
    {
        boolean exist = simpleDao.findSimple(simple);
        if (exist)
        {
            return simpleDao.updateSimple(simple);
        } else
        {
            return simpleDao.createSimple(simple);
        }
    }
}
