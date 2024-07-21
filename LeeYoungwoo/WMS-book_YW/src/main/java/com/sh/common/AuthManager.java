package com.sh.common;

import com.sh.user.model.dto.UserDto;

public class AuthManager {
    private static UserDto loginUser;

    public static boolean isLogin(){
        ;
        return loginUser != null;
    }

    public static void login(UserDto userDto) {
        loginUser = userDto;
    }

    public static UserDto getLoginUser() {
        return loginUser;
    }

    public static void logout() {
        loginUser = null;
    }
}
