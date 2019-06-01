package org.kajal.mallick.dao;

import org.junit.Before;
import org.junit.Test;
import org.kajal.mallick.entities.User;
import org.kajal.mallick.repositories.UserRepository;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class UserDaoImplTest {

    @InjectMocks
    private UserDaoImpl userDao;

    @Mock
    private UserRepository userRepository;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
    }

    @Test
    public void findAllUsers() {
        User user = new User();
        user.setUserId(1l);
        when(userRepository.findAll()).thenReturn(Collections.singletonList(user));

        List<User> users = userDao.findAllUsers();
        assertNotNull(users);
        assertEquals(1l, users.get(0).getUserId());
    }
}