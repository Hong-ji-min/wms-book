package com.sh.login.view;

import com.sh.book.view.BookMenuView;
import com.sh.inventory.view.InventoryMenuView;
import com.sh.order.view.OrderMenuView;

import java.util.Scanner;

public class MainMenuView {
    private Scanner sc = new Scanner(System.in);
    public void mainMenu() {
        String menu = """
                ===================================================
                ⚙️ 메인메뉴 ⚙️
                ===================================================
                1. 도서 관리
                2. 재고 관리
                3. 주문 관리
                0. 종료                
                ===================================================
                입력 : """;
        while (true) {
            System.out.print(menu);
            String choice = sc.next();
            switch (choice) {
                case "1" : new BookMenuView().bookMenu(); break;
                case "2" : new InventoryMenuView().inventoryMenu(); break;
                case "3" : new OrderMenuView().orderMenu(); break;
                case "0" : return;
                default :
                    System.out.println("잘못 입력하셨습니다...");
            }
        }
    }
}
