package com.sh.inventory.view;

import com.sh.inventory.controller.InventoryController;
import com.sh.order.model.dto.OrderDto;
import java.util.List;
import java.util.Scanner;

public class InventoryView {
    private final InventoryController inventoryController = new InventoryController();
    private final Scanner sc = new Scanner(System.in);

    public void inventoryMenu() {
        String inventoryMenu = """
                [재고 관리 메뉴 선택]
                 1. 재고 조회
                 2. 재고 입고
                 3. 재고 출고
                 4. 재고 이동
                 0. 뒤로 가기
                """;
        while (true) {
            System.out.println(inventoryMenu);
            String choice = sc.next();
            switch (choice) {
                case "1":
                    System.out.println("재고 조회");
                    // 재고 조회 로직 추가
                    break;
                case "2":
                    System.out.println("재고 입고");
                    // 재고 입고 로직 추가
                    break;
                case "3":
                    handleShipment();
                    break;
                case "4":
                    System.out.println("재고 이동");
                    // 재고 이동 로직 추가
                    break;
                case "0":
                    return;
                default:
                    System.out.println("❌잘못된 입력.. 다시 입력해주세요.❌");
            }
        }
    }

    private void handleShipment() {
        List<OrderDto> pendingOrders = inventoryController.getPendingOrdersWithInventory();
        if (pendingOrders.isEmpty()) {
            System.out.println("출고할 주문이 없습니다.");
            return;
        }

        System.out.println("[주문확인중 상태인 주문 목록]");
        for (OrderDto order : pendingOrders) {
            System.out.println(order);
        }

        System.out.print("출고할 주문 ID를 입력하세요: ");
        int orderId = sc.nextInt();

        boolean success = inventoryController.processShipment(orderId);
        if (success) {
            System.out.println("출고 처리가 완료되었습니다.");
        } else {
            System.out.println("출고 처리에 실패했습니다.");
        }
    }
}
