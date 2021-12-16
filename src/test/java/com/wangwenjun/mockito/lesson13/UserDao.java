package com.wangwenjun.mockito.lesson13;

public class UserDao
{

    public boolean deleteUser(User user)
    {
        throw new RuntimeException();
    }
}
