package com.sh.user.view;



import com.sh.book.view.BookView;
import com.sh.inventory.view.InventoryView;
import com.sh.order.view.OrderView;
import com.sh.user.controller.UserController;
import com.sh.user.model.entity.Role;
import com.sh.user.model.entity.User;

import java.util.Objects;
import java.util.Scanner;

public class UserView {
    OrderView orderView = new OrderView();
    InventoryView inventoryView = new InventoryView();
    BookView bookView = new BookView();
    Scanner scanner = new Scanner(System.in);
    UserController userController = new UserController();
    User user = new User();

    public void firstView() {

        String menu = """
                ========================
                     메뉴 선택
                    1. 회원 가입
                    2. 로그인
                    3. 종료
                ========================        
                """;

        while (true) {
            System.out.println(menu);
            String choice = scanner.next();
            switch (choice) {

                case "1":
                    break;

                case "2":
                    loginView();
                    break;


                case "3":
                    return;

                default:
                    System.out.println("잘못 입력하셨습니다.");


            }
        }
    }

    public void loginView() {

        String loginMenu = """
                  🤗사용자 메뉴🤗
                ================== 
                1. 로그인
                                
                2. 종료    
                ==================            
                """;

        while (true) {
            System.out.println(loginMenu);
            String choice = scanner.next();
            switch (choice) {
                case "1":
                    System.out.println("1. 아이디를 입력해주세요");
                    int userId = scanner.nextInt();
                    System.out.println("2. 비밀번호를 입력해주세요");
                    String password = scanner.next();

                    user = userController.login(userId, password);

                    if (user.getUserId() == userId && Objects.equals(user.getPassword(), password)) {
                        System.out.println(
                                "==================\n" +
                                        "로그인에 성공하셨습니다\n" +
                                        "🤗사용자 이름 : " + user.getUserName() +
                                        "\n🤗사용자 권한 : " + user.getRole() +
                                        "\n==================");
                    } else {
                        System.out.println("로그인 실패 다시 입력해 주세요");
                    }

                    System.out.println();

                    // 로그인 성공하면 mainMenu 호출
                    mainMenu();


            }
        }


    }

    public void mainMenu() {


        String mainMenu = """
                         🤗🤗🤗
                ==========================
                어떤 관리를 진행 하시겠습니까?  
                1. 재고 관리  
                2. 도서 관리  
                3. 주문 관리
                0. 로그 아웃하기
                ==========================
                입력 : """;


        while (true) {
            System.out.println(mainMenu);
            String choice = scanner.next();
            switch (choice) {

                // 재고 관리
                case "1":
                    inventoryView.inventoryManageView();
                    break;

                // 도서 관리
                case "2":
                    if (user.getRole() == Role.manager) {
                        bookView.bookMenuView();
                    } else {
                        System.out.println(user);
                        System.out.println("권한이 manager가 아닙니다. 죄송합니다.");
                    }

                    break;

                // 주문 관리
                case "3":
                    orderView.orderManageView();
                    break;

                // 종료하기
                case "0":
                    System.out.println("로그아웃 되셨습니다.");
                    return;


                default:
                    System.out.println("잘못된 입력입니다. 다시 입력해 주세요");
            }
        }

    }

}
