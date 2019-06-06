package org.kajal.mallick.dao;

import org.kajal.mallick.entities.User;
import org.kajal.mallick.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    private UserRepository userRepository;

    @Autowired
    public UserDaoImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public List<User> findUsersWithNoProject() {
        return userRepository.findAllWithNoProject();
    }

    @Override
    public List<User> findUsersWithNoTask() {
        return userRepository.findAllWithNoTask();
    }

    @Override
    public User findByUserId(long userId) {
        return userRepository.findByUserId(userId).orElse(null);
    }

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    @Transactional
    public int updateUserDetails(String firstName, String lastName, int employeeId, long userId) {
        return userRepository.updateUserDetails(firstName, lastName, employeeId, userId);
    }

    @Override
    @Transactional
    public int updateProject(Long projectId, long userId) {
        return userRepository.updateProject(projectId, userId);
    }

    @Override
    @Transactional
    public int updateTask(Long taskId, long userId) {
        return userRepository.updateTask(taskId, userId);
    }

    @Override
    @Transactional
    public int removeProjectFromUser(long projectId) {
        return userRepository.removeProjectFromUser(projectId);
    }

    @Override
    @Transactional
    public int removeTasksFromUser(List<Long> taskIds) {
        return userRepository.removeTasksFromUser(taskIds);
    }

    @Override
    @Transactional
    public void removeUserById(long userId) {
        userRepository.deleteById(userId);
    }
}
