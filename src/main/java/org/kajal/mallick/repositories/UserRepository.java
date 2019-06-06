package org.kajal.mallick.repositories;

import org.kajal.mallick.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    static final String FIND_ALL_USERS = "SELECT u FROM User u LEFT JOIN FETCH u.project LEFT JOIN u.task";
    static final String FIND_ALL_USERS_WITH_NO_PROJECT = "SELECT u FROM User u LEFT JOIN u.project p WHERE p = null";
    static final String FIND_ALL_USERS_WITH_NO_TASK = "SELECT u FROM User u LEFT JOIN FETCH u.task t WHERE t = null";
    static final String FIND_USER_BY_ID = "SELECT u FROM User u LEFT JOIN FETCH u.project LEFT JOIN u.task WHERE u.userId = :userId";
    static final String UPDATE_USER_DETAILS = "update User u set u.firstName = :firstName, u.lastName = :lastName, u.employeeId = :employeeId where u.userId = :userId";
    static final String UPDATE_USER_PROJECT = "update User u set u.project.projectId = :projectId where u.userId = :userId";
    static final String UPDATE_USER_TASK = "update User u set u.task.taskId = :taskId where u.userId = :userId";
    static final String REMOVE_PROJECT_FROM_USER = "update User u set u.project.projectId = NULL where u.project.projectId = :projectId";
    static final String REMOVE_TASKS_FROM_USER = "update User u set u.task.taskId = NULL where u.task.taskId in :taskIds";

    @Query(FIND_ALL_USERS)
    List<User> findAll();

    @Query(FIND_ALL_USERS_WITH_NO_PROJECT)
    List<User> findAllWithNoProject();

    @Query(FIND_ALL_USERS_WITH_NO_TASK)
    List<User> findAllWithNoTask();

    @Query(FIND_USER_BY_ID)
    Optional<User> findByUserId(@Param("userId") long userId);

    User save(User user);

    @Modifying
    @Query(UPDATE_USER_DETAILS)
    int updateUserDetails(@Param("firstName") String firstName, @Param("lastName") String lastName, @Param("employeeId") int employeeId, @Param("userId") long userId);


    @Modifying
    @Query(UPDATE_USER_PROJECT)
    int updateProject(@Param("projectId") Long projectId, @Param("userId") long userId);


    @Modifying
    @Query(UPDATE_USER_TASK)
    int updateTask(@Param("taskId") Long taskId, @Param("userId") long userId);

    @Modifying
    @Query(REMOVE_PROJECT_FROM_USER)
    int removeProjectFromUser(@Param("projectId") long projectId);


    @Modifying
    @Query(REMOVE_TASKS_FROM_USER)
    int removeTasksFromUser(@Param("taskIds") List<Long> taskIds);
}
