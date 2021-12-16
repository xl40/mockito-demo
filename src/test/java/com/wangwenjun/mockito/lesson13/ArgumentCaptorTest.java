package com.wangwenjun.mockito.lesson13;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ArgumentCaptorTest
{

    @Mock
    private List<String> list;

    @Mock
    private UserDao userDao;

    @Test
    public void testArgCaptor()
    {

        ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
        when(list.add("Mockito")).thenReturn(true);
        list.add("Mockito");

        // verify(list, times(1)).add("Mockito");
        verify(list, times(1)).add(captor.capture());
        assertThat(captor.getValue(), equalTo("Mockito"));

    }

    @Test
    public void testArgumentCaptor()
    {

        ArgumentCaptor<User> captor = ArgumentCaptor.forClass(User.class);
        UserService userService = new UserService(userDao);
        User user = new User("345355");

        when(userDao.deleteUser(user)).thenReturn(true);
        assertThat(userService.deleteUser(user), equalTo(true));
        verify(userDao).deleteUser(captor.capture());

        assertThat(captor.getValue().getType(), equalTo("D"));
        assertThat(user.getType(), equalTo("D"));

    }
}
