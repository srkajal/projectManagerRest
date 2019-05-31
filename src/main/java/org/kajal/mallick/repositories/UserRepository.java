package org.kajal.mallick.repositories;

import org.kajal.mallick.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findAll();

    User save(User user);
}
