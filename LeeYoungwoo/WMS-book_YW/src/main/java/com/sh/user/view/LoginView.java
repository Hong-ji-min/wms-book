package com.sh.user.view;

import com.sh.common.AuthManager;
import com.sh.user.controller.UserController;
import com.sh.user.model.UserRole;
import com.sh.user.model.dto.UserDto;

import java.util.Scanner;

public class LoginView {
    private Scanner sc = new Scanner(System.in);
    private UserController userController = new UserController();
    private UserDto userDto = null;
    public void loginMenu(){
        System.out.println("🔥 지옥의 도서창고관리 프로그램입니다. 🔥");
        int userId = 0;
        String password;
        // Id/password가 다른경우, role이 다른경우 검증
        do {
            System.out.print("> ID를 입력해주세요 : ");
            userId = sc.nextInt();
            System.out.print("> 비밀번호를 입력해주세요 : ");
            password = sc.next();
            userDto = userController.login(userId, password);
        } while (userDto == null || !userDto.getRole().equals(UserRole.manager));

        // 로그인 성공시 AuthManager에 로그인정보 담고, 메인메뉴 호출
        AuthManager.login(userDto);

        MainMenuView mainMenuView = new MainMenuView();
        mainMenuView.mainMenu();
    }

}
