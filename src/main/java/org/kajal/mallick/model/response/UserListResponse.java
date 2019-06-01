package org.kajal.mallick.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.kajal.mallick.model.UserDto;

import java.util.ArrayList;
import java.util.List;

public class UserListResponse {
    private List<UserDto> users;
    @JsonProperty("response_detail")
    private BaseResponse baseResponse;

    public UserListResponse() {
    }

    public BaseResponse getBaseResponse() {
        return baseResponse;
    }

    public void setBaseResponse(BaseResponse baseResponse) {
        this.baseResponse = baseResponse;
    }

    public List<UserDto> getUsers() {
        return users == null ? new ArrayList<>() : users;
    }

    public void setUsers(List<UserDto> users) {
        this.users = users;
    }
}
