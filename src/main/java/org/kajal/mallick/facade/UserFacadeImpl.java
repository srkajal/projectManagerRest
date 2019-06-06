package org.kajal.mallick.facade;

import org.kajal.mallick.dao.UserDao;
import org.kajal.mallick.entities.User;
import org.kajal.mallick.model.request.UserRequest;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserFacadeImpl implements UserFacade {
    private UserDao userDao;

    public UserFacadeImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public List<User> findAllUsers() {
        return userDao.findAllUsers();
    }

    @Override
    public List<User> findUsersWithNoProject() {
        return userDao.findUsersWithNoProject();
    }

    @Override
    public List<User> findUsersWithNoTask() {
        return userDao.findUsersWithNoTask();
    }

    @Override
    public User findByUserId(long userId) {
        return userDao.findByUserId(userId);
    }

    @Override
    public User createUser(UserRequest userRequest) {
        return userDao.saveUser(new User(userRequest.getFirstName(), userRequest.getLastName(), userRequest.getEmployeeId()));
    }

    @Override
    public int updateUserDetails(UserRequest userRequest) {
        return userDao.updateUserDetails(userRequest.getFirstName(), userRequest.getLastName(), userRequest.getEmployeeId(), userRequest.getUserId());
    }

    @Override
    public void removeUserById(long userId) {
        userDao.removeUserById(userId);
    }
}
