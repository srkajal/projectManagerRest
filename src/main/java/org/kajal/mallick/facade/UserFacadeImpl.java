package org.kajal.mallick.facade;

import org.kajal.mallick.dao.UserDao;
import org.kajal.mallick.entities.User;
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
}
