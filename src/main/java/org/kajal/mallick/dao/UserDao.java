package org.kajal.mallick.dao;

import org.kajal.mallick.entities.User;

import java.util.List;

public interface UserDao {
    List<User> findAllUsers();
}
