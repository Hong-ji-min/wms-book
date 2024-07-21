package com.sh.inventory.view;

import com.sh.inventory.controller.InventoryController;
import com.sh.order.controller.OrderController;
import com.sh.order.model.dto.OrderDto;
import com.sh.order.model.dto.Status;
import com.sh.order.view.OrderResultView;

import java.util.List;
import java.util.Scanner;

public class InventoryView {
    public static void main(String[] args) {
        new InventoryView().inventoryMenu();
    }
    private Scanner sc = new Scanner(System.in);
    InventoryController inventoryController = new InventoryController();
    OrderController orderController = new OrderController();

    public void inventoryMenu(){
        String menu = """
        =====================
        <재고 관리 메뉴 선택>
        1. 재고 현황 조회
        2. 입고
        3. 출고
        4. 재고 이동
        0. 뒤로가기
        =====================
        입력 : """;

        while(true){
            System.out.print(menu);
            String choice = sc.next();
            switch(choice){
                case "1" :  break;
                case "2" :  break;
                case "3" :  outbound(); break;
                case "4" :  break;
                case "0" :  return;
                default: System.out.println("잘못 입력하셨습니다.");
            }
        }
    }
    private void outbound(){
        // 주문확인중 상태의 주문 조회
        findOrderByConfirmStatus();

        // 출고할 주문번호 선택
        System.out.print("> 출고할 주문번호 입력 : ");
        int orderId = sc.nextInt();

        // 입력한 주문번호에 따라 tbl_inventory의 재고를 update. 성공하면 true, 실패하면 false
        boolean result = inventoryController.findOrderBook(orderId);
        if(result)
            System.out.println("✅ 출고 완료 ✅");
        else
            System.out.println("❌ 출고 실패 ❌");
    }

    private void findOrderByConfirmStatus() {
        System.out.println("[주문확인중] 상태의 주문을 조회합니다\n");
        String status = "주문확인중";
        List<OrderDto> orderList = null;
        try {
            orderList = orderController.findOrderByStatus(Status.valueOf(status));
            OrderResultView.displayOrderByStatus(orderList);
        } catch (IllegalArgumentException e) {
            if (orderList.isEmpty())
                System.out.println("❌주문정보가 없습니다.❌\n");
        }
    }
}
