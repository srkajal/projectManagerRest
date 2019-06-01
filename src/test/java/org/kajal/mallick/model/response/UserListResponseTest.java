package org.kajal.mallick.model.response;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.kajal.mallick.model.UserDto;
import org.mockito.InjectMocks;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.Assert.assertEquals;
import static org.mockito.MockitoAnnotations.initMocks;

@RunWith(Parameterized.class)
public class UserListResponseTest {
    private final long userId;
    private final String status;
    @InjectMocks
    private UserListResponse userListResponse;

    public UserListResponseTest(long userId, String status) {
        this.userId = userId;
        this.status = status;
    }

    @Parameterized.Parameters(name
            = "{index}: Test with USER_ID={0}, STATUS={1}")
    public static Iterable<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {1l, "SUCCESS"},
                {2l, "FAILED"}
        });
    }

    @Before
    public void setUp() throws Exception {
        initMocks(this);
    }

    @Test
    public void getBaseResponse() {
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setStatus(status);
        userListResponse.setBaseResponse(baseResponse);
        assertEquals(status, userListResponse.getBaseResponse().getStatus());
    }

    @Test
    public void getUsers() {
        UserDto userDto = new UserDto();
        userDto.setUserId(userId);

        userListResponse.setUsers(Collections.singletonList(userDto));

        assertEquals(userId, userListResponse.getUsers().get(0).getUserId());
    }
}