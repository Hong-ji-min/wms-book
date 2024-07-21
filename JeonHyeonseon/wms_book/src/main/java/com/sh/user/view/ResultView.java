package com.sh.user.view;

import com.sh.common.AuthManager;
import com.sh.user.model.dto.UserDto;

public class ResultView {

    public static void displayLoginResult(UserDto userDto) {
        if (userDto != null) {
            UserDto loginUser = AuthManager.getLoginUser();
            System.out.printf("안녕하세요. %s님\n", loginUser.getUserName());
            System.out.println();
        } else {
            System.out.println("아이디/비밀번호가 일치하지 않습니다.");
        }
    }
}
