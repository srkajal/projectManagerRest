package org.kajal.mallick.facade;

import org.kajal.mallick.entities.User;

import java.util.List;

public interface UserFacade {
    List<User> findAllUsers();
}
