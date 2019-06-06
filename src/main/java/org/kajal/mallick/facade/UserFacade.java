package org.kajal.mallick.facade;

import org.kajal.mallick.entities.User;
import org.kajal.mallick.model.request.UserRequest;

import java.util.List;

public interface UserFacade {
    List<User> findAllUsers();

    List<User> findUsersWithNoProject();

    List<User> findUsersWithNoTask();

    User findByUserId(long userId);

    User createUser(UserRequest userRequest);

    int updateUserDetails(UserRequest userRequest);

    void removeUserById(long userId);
}
