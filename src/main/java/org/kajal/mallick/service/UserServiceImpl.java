package org.kajal.mallick.service;

import org.kajal.mallick.entities.User;
import org.kajal.mallick.facade.UserFacade;
import org.kajal.mallick.model.UserDto;
import org.kajal.mallick.model.response.BaseResponse;
import org.kajal.mallick.model.response.UserListResponse;
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
        UserListResponse userListResponse = new UserListResponse();
        BaseResponse baseResponse;

        List<User> userList = userFacade.findAllUsers();

        if (!CollectionUtils.isEmpty(userList)) {
            userList
                    .forEach(user -> userListResponse
                            .getUsers()
                            .add(new UserDto(user)));
            baseResponse = new BaseResponse(HttpStatus.FOUND.getReasonPhrase(), HttpStatus.FOUND.value(), "Number of users found " + userList.size());

            logger.info("Find number of user:{}", userList.size());
        } else {
            baseResponse = new BaseResponse(HttpStatus.NOT_FOUND.getReasonPhrase(), HttpStatus.NOT_FOUND.value(), "No User found");
            logger.info("No User found");
        }

        userListResponse.setBaseResponse(baseResponse);

        return userListResponse;
    }
}
