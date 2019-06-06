package org.kajal.mallick.facade;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.kajal.mallick.BaseDataTest;
import org.kajal.mallick.dao.UserDao;
import org.kajal.mallick.entities.User;
import org.kajal.mallick.model.request.UserRequest;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

@RunWith(Parameterized.class)
public class UserFacadeImplTest {
    private final User userTestObject;
    @InjectMocks
    private UserFacadeImpl userFacade;
    @Mock
    private UserDao userDao;

    public UserFacadeImplTest(User userTestObject) {
        this.userTestObject = userTestObject;
    }

    @Parameterized.Parameters(name
            = "{index}: Test with USER={0}")
    public static Iterable<Object[]> data() {
        return BaseDataTest.userData;
    }

    @Before
    public void setUp() throws Exception {
        initMocks(this);
    }

    @Test
    public void findAllUsers() {
        when(userDao.findAllUsers()).thenReturn(Collections.singletonList(userTestObject));

        List<User> users = userFacade.findAllUsers();

        assertNotNull(users);
        assertEquals(userTestObject.getUserId(), users.get(0).getUserId());
    }

    @Test
    public void findUsersWithNoProject() {
        when(userDao.findUsersWithNoProject()).thenReturn(Collections.singletonList(userTestObject));

        List<User> users = userFacade.findUsersWithNoProject();

        assertNotNull(users);
        assertEquals(userTestObject.getUserId(), users.get(0).getUserId());
    }

    @Test
    public void findUsersWithNoTask() {
        when(userDao.findUsersWithNoTask()).thenReturn(Collections.singletonList(userTestObject));

        List<User> users = userFacade.findUsersWithNoTask();

        assertNotNull(users);
        assertEquals(userTestObject.getUserId(), users.get(0).getUserId());
    }

    @Test
    public void findByUserId() {
        when(userDao.findByUserId(anyLong())).thenReturn(userTestObject);

        User user = userFacade.findByUserId(userTestObject.getUserId());

        assertEquals(userTestObject.getUserId(), user.getUserId());
    }

    @Test
    public void createUser() {
        when(userDao.saveUser(any(User.class))).thenReturn(userTestObject);

        UserRequest userRequest = new UserRequest();
        userRequest.setFirstName(userTestObject.getFirstName());
        userRequest.setLastName(userTestObject.getLastName());
        userRequest.setEmployeeId(userTestObject.getEmployeeId());
        userRequest.setProjectId(userTestObject.getProject().getProjectId());
        userRequest.setTaskId(userTestObject.getTask().getTaskId());

        User user = userFacade.createUser(userRequest);

        assertEquals(userTestObject.getUserId(), user.getUserId());
    }

    @Test
    public void updateUserDetails() {
        when(userDao.updateUserDetails(anyString(), anyString(), anyInt(), anyLong())).thenReturn(1);

        UserRequest userRequest = new UserRequest();
        userRequest.setFirstName(userTestObject.getFirstName());
        userRequest.setLastName(userTestObject.getLastName());
        userRequest.setEmployeeId(userTestObject.getEmployeeId());
        userRequest.setProjectId(userTestObject.getProject().getProjectId());
        userRequest.setTaskId(userTestObject.getTask().getTaskId());
        userRequest.setUserId(userTestObject.getUserId());

        int number = userFacade.updateUserDetails(userRequest);

        assertTrue(number > 0);
    }

    @Test
    public void removeUserById() {
        doNothing().when(userDao).removeUserById(anyLong());

        userFacade.removeUserById(userTestObject.getUserId());

        verify(userDao, times(1)).removeUserById(anyLong());
    }
}