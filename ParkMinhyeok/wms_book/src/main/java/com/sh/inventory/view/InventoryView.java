package com.sh.inventory.view;

import com.sh.inventory.controller.InventoryController;
import com.sh.order.controller.OrderController;
import com.sh.order.model.dto.OrderDto;
import com.sh.order.model.entity.Status;
import com.sh.order.view.OrderResultView;

import java.util.List;
import java.util.Scanner;

public class InventoryView {
    OrderController orderController = new OrderController();
    InventoryController inventoryController = new InventoryController();
    Scanner sc = new Scanner(System.in);
    public void inventoryMenu() {
        String menu = """
                ===========================================
                재고 관리 메뉴
                1. 재고 조회
                2. 재고 입고
                3. 재고 출고
                4. 재고 이동
                0. 뒤로가기
                ===========================================
                """;
        System.out.println(menu);
        String choice = sc.next();
        switch (choice) {
            case "1" :
                System.out.println("재고 조회");
                break;
            case "2" :
                System.out.println("재고 입고");
                break;
            case "3" :
                exWarehousingMenu();
                break;
            case "4" :
                System.out.println("재고 이동");
                break;
            case "0" :
                return;
            default:
                System.out.println("잘못된 입력입니다.");
        }
    }

    private void exWarehousingMenu() {
        List<OrderDto> orderByStatusList = orderController.findOrderByStatus(Status.주문확인중);
        OrderResultView.displayOrderByStatus(orderByStatusList);
        System.out.println("출고처리할 주문 번호를 입력해주세요.");
        int orderId = sc.nextInt();
        inventoryController.exWarehousing(orderId);
    }
}
