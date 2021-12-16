package com.wangwenjun.mockito.lesson13;

public class UserService
{

    private final UserDao userDao;


    public UserService(UserDao userDao)
    {
        this.userDao = userDao;
    }

    public boolean deleteUser(User user)
    {
        user.setType("D");
        return this.userDao.deleteUser(user);
    }
}
