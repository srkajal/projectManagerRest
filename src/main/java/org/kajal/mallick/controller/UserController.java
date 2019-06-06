package org.kajal.mallick.controller;

import org.kajal.mallick.model.request.UserRequest;
import org.kajal.mallick.model.response.BaseResponse;
import org.kajal.mallick.model.response.UserListResponse;
import org.kajal.mallick.model.response.UserResponse;
import org.kajal.mallick.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/user")
class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/findAllUsers")
    UserListResponse findAllUsers() {
        return userService.findAllUsers();
    }

    @GetMapping(value = "/findUsersWithNoProject")
    UserListResponse findUsersWithNoProject() {
        return userService.findUsersWithNoProject();
    }

    @GetMapping(value = "/findUsersWithNoTask")
    UserListResponse findUsersWithNoTask() {
        return userService.findUsersWithNoTask();
    }

    @GetMapping("/findUserById/{userId}")
    public UserResponse findUserById(@PathVariable("userId") long userId) {
        return userService.findByUserId(userId);
    }

    @PostMapping("/createUser")
    public @ResponseBody
    BaseResponse createUser(@RequestBody @Valid UserRequest userRequest) {
        return userService.createUser(userRequest);
    }

    @PostMapping("/updateUser")
    public @ResponseBody
    BaseResponse updateUser(@RequestBody @Valid UserRequest userRequest) {
        return userService.updateUserDetails(userRequest);
    }

    @GetMapping("/delete/{userId}")
    public BaseResponse removeUserById(@PathVariable("userId") long userId) {
        return userService.removeUserById(userId);
    }
}
