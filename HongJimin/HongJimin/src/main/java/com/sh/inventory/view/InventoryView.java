package com.sh.inventory.view;

import com.sh.inventory.controller.InventoryController;
import com.sh.order.model.entity.Status;
import com.sh.order.view.OrderResultView;

import java.util.Scanner;

public class InventoryView {
    Scanner sc = new Scanner(System.in);
    InventoryController inventoryController = new InventoryController();

    public void inventoryMenu() {
        String inventoryMenu = """
                ==============================
                 📦재고 관리 메뉴를 선택해주세요📦
                        1. 재고 조회
                        2. 재고 입고
                        3. 재고 출고
                        4. 재고 이동
                        5. 뒤로 가기
                ==============================
                """;
        System.out.println(inventoryMenu);
        String choice = sc.next();
        switch (choice) {
            case "1":
                // 1. 재고 조회
                inventoryController.findInventory();
                break;
            case "2":
                // 2. 재고 입고
                inventoryController.inboundInventory();
                break;
            case "3":
                // 3. 재고 출고
                inventoryController.outboundInventory(Status.주문확인중);

                break;
            case "4":
                // 4. 재고 이동
                inventoryController.moveInventory();
                break;
            case "5":
                // 5. 뒤로 가기
                return;
            default:
                System.out.println("잘못된 입력입니다. 다시 입력해주세요😥");
        }
    }
}
