package com.sh.user.view;

import com.sh.book.view.BookView;
import com.sh.common.AuthManager;
import com.sh.inventory.view.InventoryView;
import com.sh.order.view.OrderView;
import com.sh.user.controller.UserController;
import com.sh.user.model.dto.UserDto;

import java.util.Scanner;

import static com.sh.common.AuthManager.getLoginUser;

public class UserView {
    private UserController userController = new UserController();
    private Scanner sc = new Scanner(System.in);

    public void loginMenu(){
        String menu = """
                       [등록]
                =====================
                      1. 로그인
                      2. 회원가입
                      0. 종료
                =====================
                입력 : """;
        while (true) {
            System.out.print(menu);
            String choice = sc.next();
            switch (choice) {
                case "1" :
                    login();
                    break;
                case "2" : break;
                case "0" : return;
                default:
                    System.out.println("잘못 입력하셨습니다. 다시 입력해주세요.");
            }
        }
    }

    private void login() {
        System.out.print("아이디 : ");
        int userId = sc.nextInt();
        sc.nextLine();
        System.out.print("비밀번호 : ");
        String password = sc.nextLine();
        userController.login(userId, password);
        if (AuthManager.isLogin()) {
            mainMenu();
        }
    }

    // 로그인 성공시 메뉴 선택 가능
    public void mainMenu() {
        String menu = """
                     [메뉴 선택]
                =====================
                     1. 도서관리
                     2. 재고관리
                     3. 주문관리
                     0. 돌아가기
                =====================
                입력 : """;
        while(true) {
            System.out.print(menu);
            String choice = sc.next();
            switch (choice) {
                case "1" :
                    BookView bookView = new BookView();
                    bookView.bookMenu();
                    break;
                case "2" :
                    InventoryView inventoryView = new InventoryView();
                    inventoryView.inventoryMenu();
                    break;
                case "3" :
                    OrderView orderView = new OrderView();
                    orderView.orderMenu();
                    break;
                case "0" : return;
                default:
                    System.out.println("잘못 입력하셨습니다. 다시 입력해주세요.");
            }
        }
    }
}
