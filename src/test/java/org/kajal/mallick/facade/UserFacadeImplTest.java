package org.kajal.mallick.facade;

import org.junit.Before;
import org.junit.Test;
import org.kajal.mallick.dao.UserDao;
import org.kajal.mallick.entities.User;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class UserFacadeImplTest {
    @InjectMocks
    private UserFacadeImpl userFacade;

    @Mock
    private UserDao userDao;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
    }

    @Test
    public void findAllUsers() {
        User user = new User();
        user.setUserId(1l);

        when(userDao.findAllUsers()).thenReturn(Collections.singletonList(user));

        List<User> users = userFacade.findAllUsers();

        assertNotNull(users);
        assertEquals(1l, users.get(0).getUserId());
    }
}