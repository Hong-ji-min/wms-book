package com.sh.user.controller;

import com.sh.common.AuthManager;
import com.sh.user.model.entity.User;
import com.sh.user.model.service.UserService;

public class UserController {
    AuthManager authManager = new AuthManager();
    UserService userService = new UserService();

    public User login(int userId, String password) {
        return userService.login(userId, password);
    }


}
