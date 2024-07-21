package com.sh.user.view;

import com.sh.book.model.dto.BookDto;
import com.sh.user.model.UserRole;
import com.sh.user.model.dto.UserDto;

import java.util.List;

public class UserResultView {
    public static void displayLoginMenu(UserDto userDto) {
        if (userDto == null) {
            System.out.println("❌ 등록되지 않은 사용자입니다. ❌");
        } else if (!userDto.getRole().equals(UserRole.manager)) {
            System.out.println("😅 메니져만 이용할 수 있습니다. 😅");
        } else {
            System.out.printf("😀 %s님 로그인 성공. 환영합니다!! 😀\n", userDto.getUserName());
        }
    }


}
