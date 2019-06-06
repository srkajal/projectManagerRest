package org.kajal.mallick.dao;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.kajal.mallick.BaseDataTest;
import org.kajal.mallick.entities.User;
import org.kajal.mallick.repositories.UserRepository;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

@RunWith(Parameterized.class)
public class UserDaoImplTest {

    private final User userTestObject;
    @InjectMocks
    private UserDaoImpl userDao;
    @Mock
    private UserRepository userRepository;

    public UserDaoImplTest(User userTestObject) {
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
        when(userRepository.findAll()).thenReturn(Collections.singletonList(userTestObject));

        List<User> users = userDao.findAllUsers();
        assertNotNull(users);
        assertEquals(userTestObject.getUserId(), users.get(0).getUserId());
    }

    @Test
    public void findUsersWithNoProject() {
        when(userRepository.findAllWithNoProject()).thenReturn(Collections.singletonList(userTestObject));

        List<User> users = userDao.findUsersWithNoProject();
        assertNotNull(users);
        assertEquals(userTestObject.getUserId(), users.get(0).getUserId());
    }

    @Test
    public void findUsersWithNoTask() {
        when(userRepository.findAllWithNoTask()).thenReturn(Collections.singletonList(userTestObject));

        List<User> users = userDao.findUsersWithNoTask();
        assertNotNull(users);
        assertEquals(userTestObject.getUserId(), users.get(0).getUserId());
    }

    @Test
    public void findByUserId() {
        when(userRepository.findByUserId(anyLong())).thenReturn(Optional.of(userTestObject));

        User user = userDao.findByUserId(userTestObject.getUserId());

        assertEquals(userTestObject.getUserId(), user.getUserId());
    }

    @Test
    public void saveUser() {
        when(userRepository.save(any(User.class))).thenReturn(userTestObject);

        User user = userDao.saveUser(userTestObject);

        assertEquals(userTestObject.getUserId(), user.getUserId());
    }

    @Test
    public void updateUserDetails() {
        when(userRepository.updateUserDetails(anyString(), anyString(), anyInt(), anyLong())).thenReturn(1);

        int number = userDao.updateUserDetails(userTestObject.getFirstName(), userTestObject.getLastName(), userTestObject.getEmployeeId(), userTestObject.getUserId());

        assertTrue(number > 0);
    }

    @Test
    public void updateProject() {
        when(userRepository.updateProject(anyLong(), anyLong())).thenReturn(1);

        int number = userDao.updateProject(userTestObject.getProject().getProjectId(), userTestObject.getUserId());

        assertTrue(number > 0);
    }

    @Test
    public void updateTask() {
        when(userRepository.updateTask(anyLong(), anyLong())).thenReturn(1);

        int number = userDao.updateTask(userTestObject.getTask().getTaskId(), userTestObject.getUserId());

        assertTrue(number > 0);
    }

    @Test
    public void removeProjectFromUser() {
        when(userRepository.removeProjectFromUser(anyLong())).thenReturn(1);

        int number = userDao.removeProjectFromUser(userTestObject.getProject().getProjectId());

        assertTrue(number > 0);
    }

    @Test
    public void removeTasksFromUser() {
        when(userRepository.removeTasksFromUser(anyList())).thenReturn(1);

        int number = userDao.removeTasksFromUser(Collections.singletonList(userTestObject.getTask().getTaskId()));

        assertTrue(number > 0);
    }

    @Test
    public void removeUserById() {
        doNothing().when(userRepository).deleteById(anyLong());

        userDao.removeUserById(userTestObject.getUserId());

        verify(userRepository, times(1)).deleteById(anyLong());

    }
}