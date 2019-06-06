package org.kajal.mallick.service;

import org.kajal.mallick.entities.User;
import org.kajal.mallick.exception.UserException;
import org.kajal.mallick.facade.UserFacade;
import org.kajal.mallick.model.UserDto;
import org.kajal.mallick.model.request.UserRequest;
import org.kajal.mallick.model.response.BaseResponse;
import org.kajal.mallick.model.response.UserListResponse;
import org.kajal.mallick.model.response.UserResponse;
import org.kajal.mallick.util.TaskManagerConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    private UserFacade userFacade;

    @Autowired
    public UserServiceImpl(UserFacade userFacade) {
        this.userFacade = userFacade;
    }

    @Override
    public UserListResponse findAllUsers() {
        List<User> userList = userFacade.findAllUsers();

        return generateUsersResponseFromList(userList, TaskManagerConstant.USERS_SUCCESS_MESSAGE, TaskManagerConstant.USERS_FAILURE_MESSAGE);
    }

    @Override
    public UserListResponse findUsersWithNoProject() {
        List<User> userList = userFacade.findUsersWithNoProject();

        return generateUsersResponseFromList(userList, TaskManagerConstant.USERS_SUCCESS_MESSAGE, TaskManagerConstant.USERS_FAILURE_MESSAGE);
    }

    @Override
    public UserListResponse findUsersWithNoTask() {
        List<User> userList = userFacade.findUsersWithNoTask();

        return generateUsersResponseFromList(userList, TaskManagerConstant.USERS_SUCCESS_MESSAGE, TaskManagerConstant.USERS_FAILURE_MESSAGE);
    }

    @Override
    public UserResponse findByUserId(long userId) {
        UserResponse userResponse = new UserResponse();
        BaseResponse baseResponse;

        if (userId <= 0) {
            throw new UserException("UserId should not be less than 1");
        }

        User user = userFacade.findByUserId(userId);

        if (user != null) {
            userResponse.setUserDto(new UserDto(user));
            baseResponse = new BaseResponse(HttpStatus.FOUND.getReasonPhrase(), HttpStatus.FOUND.value(), "User found for Id:" + userId);
            logger.info("Find user by id:{}", userId);
        } else {
            baseResponse = new BaseResponse(HttpStatus.NOT_FOUND.getReasonPhrase(), HttpStatus.NOT_FOUND.value(), "No User found for Id:" + userId);
            logger.info("User not found for id:{}", userId);
        }

        userResponse.setBaseResponse(baseResponse);

        return userResponse;
    }

    @Override
    public BaseResponse createUser(UserRequest userRequest) {
        User savedUser = userFacade.createUser(userRequest);

        if (savedUser != null) {
            logger.info("User saved successfully :{}", userRequest.getFirstName());
            return new BaseResponse(HttpStatus.CREATED.getReasonPhrase(), HttpStatus.CREATED.value(), "User saved successfully");
        } else {
            logger.info("Unable to save the user :{}", userRequest.getFirstName());
            return new BaseResponse(HttpStatus.UNPROCESSABLE_ENTITY.getReasonPhrase(), HttpStatus.UNPROCESSABLE_ENTITY.value(), "Failed to save user");
        }
    }

    @Override
    public BaseResponse updateUserDetails(UserRequest userRequest) {
        BaseResponse baseResponse;

        if (userRequest.getUserId() <= 0) {
            throw new UserException("UserId should not be less than 1");
        }

        int rowUpdated = userFacade.updateUserDetails(userRequest);

        if (rowUpdated > 0) {
            baseResponse = new BaseResponse(HttpStatus.OK.getReasonPhrase(), HttpStatus.OK.value(), "User details updated for id:" + userRequest.getUserId());
            logger.info("User details updated for id:{}", userRequest.getUserId());
        } else {
            baseResponse = new BaseResponse(HttpStatus.NOT_MODIFIED.getReasonPhrase(), HttpStatus.NOT_MODIFIED.value(), "Failed to update User details for id:" + userRequest.getUserId());
            logger.info("Failed to update User details for id:{}", userRequest.getUserId());
        }

        return baseResponse;
    }

    @Override
    public BaseResponse removeUserById(long userId) {

        userFacade.removeUserById(userId);

        logger.info("User deleted by id:{}", userId);

        return new BaseResponse(HttpStatus.OK.getReasonPhrase(), HttpStatus.OK.value(), "User deleted successfully");


    }

    private UserListResponse generateUsersResponseFromList(List<User> userList, String successMessage, String errorMessage) {
        UserListResponse userListResponse = new UserListResponse();
        BaseResponse baseResponse;

        if (!CollectionUtils.isEmpty(userList)) {
            userList
                    .forEach(user -> userListResponse
                            .getUsers()
                            .add(new UserDto(user)));
            baseResponse = new BaseResponse(HttpStatus.FOUND.getReasonPhrase(), HttpStatus.FOUND.value(), String.format(successMessage, userList.size()));

            logger.info(String.format(successMessage, userList.size()));
        } else {
            baseResponse = new BaseResponse(HttpStatus.NOT_FOUND.getReasonPhrase(), HttpStatus.NOT_FOUND.value(), errorMessage);
            logger.info(errorMessage);
        }

        userListResponse.setBaseResponse(baseResponse);

        return userListResponse;
    }
}
