package org.kajal.mallick.dao;

import org.kajal.mallick.entities.User;

import java.util.List;

public interface UserDao {
    List<User> findAllUsers();

    User findByUserId(long userId);

    List<User> findUsersWithNoProject();

    List<User> findUsersWithNoTask();

    User saveUser(User user);

    int updateUserDetails(String firstName, String lastName, int employeeId, long userId);

    int updateProject(Long projectId, long userId);

    int updateTask(Long taskId, long userId);

    int removeProjectFromUser(long projectId);

    int removeTasksFromUser(List<Long> taskIds);

    void removeUserById(long userId);
}
