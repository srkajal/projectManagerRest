package org.kajal.mallick.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.kajal.mallick.BaseDataTest;
import org.kajal.mallick.entities.User;
import org.kajal.mallick.facade.UserFacade;
import org.kajal.mallick.model.request.UserRequest;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;

import java.util.Collections;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

@RunWith(Parameterized.class)
public class UserServiceImplTest {
    private final User userTestObject;
    @InjectMocks
    private UserServiceImpl userService;
    @Mock
    private UserFacade userFacade;

    public UserServiceImplTest(User userTestObject) {
        this.userTestObject = userTestObject;
    }

    @Parameterized.Parameters(name
            = "{index}: Test with PROJECT={0}")
    public static Iterable<Object[]> data() {
        return BaseDataTest.userData;
    }

    @Before
    public void setUp() throws Exception {
        initMocks(this);
    }

    @Test
    public void findAllUsers() {
        when(userFacade.findAllUsers()).thenReturn(Collections.singletonList(userTestObject));

        assertEquals(userTestObject.getUserId(), userService.findAllUsers().getUsers().get(0).getUserId());
    }

    @Test
    public void findUsersWithNoProject() {
        when(userFacade.findUsersWithNoProject()).thenReturn(Collections.singletonList(userTestObject));

        assertEquals(userTestObject.getUserId(), userService.findUsersWithNoProject().getUsers().get(0).getUserId());
    }

    @Test
    public void findUsersWithNoTask() {
        when(userFacade.findUsersWithNoTask()).thenReturn(Collections.singletonList(userTestObject));

        assertEquals(userTestObject.getUserId(), userService.findUsersWithNoTask().getUsers().get(0).getUserId());
    }

    @Test
    public void findByUserId() {
        when(userFacade.findByUserId(anyLong())).thenReturn(userTestObject);

        assertEquals(userTestObject.getUserId(), userService.findByUserId(userTestObject.getUserId()).getUserDto().getUserId());
    }

    @Test
    public void createUser() {
        when(userFacade.createUser(any(UserRequest.class))).thenReturn(userTestObject);

        UserRequest userRequest = new UserRequest();
        userRequest.setFirstName(userTestObject.getFirstName());
        userRequest.setLastName(userTestObject.getLastName());
        userRequest.setEmployeeId(userTestObject.getEmployeeId());
        userRequest.setProjectId(userTestObject.getProject().getProjectId());
        userRequest.setTaskId(userTestObject.getTask().getTaskId());

        assertEquals(HttpStatus.CREATED.value(), userService.createUser(userRequest).getCode().intValue());
    }

    @Test
    public void updateUserDetais() {
        when(userFacade.updateUserDetails(any(UserRequest.class))).thenReturn(1);

        UserRequest userRequest = new UserRequest();
        userRequest.setFirstName(userTestObject.getFirstName());
        userRequest.setLastName(userTestObject.getLastName());
        userRequest.setEmployeeId(userTestObject.getEmployeeId());
        userRequest.setProjectId(userTestObject.getProject().getProjectId());
        userRequest.setTaskId(userTestObject.getTask().getTaskId());
        userRequest.setUserId(userTestObject.getUserId());


        assertEquals(HttpStatus.OK.value(), userService.updateUserDetails(userRequest).getCode().intValue());
    }

    @Test
    public void removeUserById() {
        doNothing().when(userFacade).removeUserById(anyLong());

        assertEquals(HttpStatus.OK.value(), userService.removeUserById(userTestObject.getUserId()).getCode().intValue());

        verify(userFacade, times(1)).removeUserById(anyLong());
    }
}