package org.kajal.mallick.service;

import org.kajal.mallick.model.request.UserRequest;
import org.kajal.mallick.model.response.BaseResponse;
import org.kajal.mallick.model.response.UserListResponse;
import org.kajal.mallick.model.response.UserResponse;

public interface UserService {
    UserListResponse findAllUsers();

    UserListResponse findUsersWithNoProject();

    UserListResponse findUsersWithNoTask();

    UserResponse findByUserId(long userId);

    BaseResponse createUser(UserRequest userRequest);

    BaseResponse updateUserDetails(UserRequest userRequest);

    BaseResponse removeUserById(long userId);
}
