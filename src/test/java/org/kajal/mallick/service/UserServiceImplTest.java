package org.kajal.mallick.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.kajal.mallick.entities.Project;
import org.kajal.mallick.entities.Task;
import org.kajal.mallick.entities.User;
import org.kajal.mallick.facade.UserFacade;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

@RunWith(Parameterized.class)
public class UserServiceImplTest {
    private final List<User> users;
    private final int statusValue;
    @InjectMocks
    private UserServiceImpl userService;
    @Mock
    private UserFacade userFacade;

    public UserServiceImplTest(List<User> users, int statusValue) {
        this.users = users;
        this.statusValue = statusValue;
    }

    @Parameterized.Parameters(name
            = "{index}: Test with PROJECT_LIST={0}, EXPECTED={1}")
    public static Iterable<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {Collections.singletonList(new User(1l, "name1", "name2", 1, new Project(), new Task())), HttpStatus.FOUND.value()},
                {Collections.EMPTY_LIST, HttpStatus.NOT_FOUND.value()}
        });
    }

    @Before
    public void setUp() throws Exception {
        initMocks(this);
    }

    @Test
    public void findAllUsers() {
        when(userFacade.findAllUsers()).thenReturn(users);

        assertEquals(statusValue, userService.findAllUsers().getBaseResponse().getCode().intValue());
    }
}