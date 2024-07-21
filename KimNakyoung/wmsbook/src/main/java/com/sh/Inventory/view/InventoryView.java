package com.sh.Inventory.view;

import com.sh.Inventory.controller.InventoryController;
import com.sh.order.controller.OrderController;
import com.sh.order.model.dto.OrderDto;
import com.sh.order.model.dto.OrderItemDto;
import com.sh.order.model.dto.Status;
import com.sh.order.view.OrderView;

import java.util.List;
import java.util.Scanner;

// 1. 재고 조회 실시간 재고 현황 조회 (도서별, 창고별 재고량)
// 2. 재고 입고 (새로운 도서 입고 처리 (입고량, 입고 날짜, 공급업체 등 정보 입력))
// 3. 재고 출고 (출고 처리 (출고량, 출고 날짜, 출고 목적 등 정보 입력))
// 4. 재고 이동 (창고 간 재고 이동 처리 (이동량, 이동 날짜 등 정보 입력))
public class InventoryView {
    private InventoryController inventoryController = new InventoryController();

    private OrderController orderController = new OrderController();

    private OrderView orderView = new OrderView();

    private Scanner sc = new Scanner(System.in);

    public void mainInventoryMenu() {
        String InventoryMenu = """
                =====================
                1.재고 조회
                2.재고 입고
                3.재고 출고
                4.재고 이동
                0.종료
                =====================
                입력:""";
        System.out.println("재고관리를 선택하셨습니다.");
        while (true) {
            System.out.print(InventoryMenu);
            String choice = sc.next();
            switch (choice) {
                case "1" :
                    System.out.println("재고 조회합니다.");
                    break;
                case "2" :
                    System.out.println("재고 입고합니다.");
                    break;
                case "3" :
//                    System.out.println("재고 출고합니다.");
                    ObInventory();
                    break;
                case "4" :
                    System.out.println("재고 이동합니다.");
                    break;
                case "0" :
                    return;
                default:
                    System.out.println("잘못 입력 하셨습니다.");
            }
        }
    }

//    도서관리자는 재고메뉴중 주문내역을 바탕으로 출고처리 할수 있습니다.

//    출고를 선택하면, 주문확인중상태인 주문내역을 목록으로 조회할 수 있습니다.

//    특정주문을 선택하면 주문아이템별로 출고된 내역만큼 차감처리되어야 합니다.

//    모든 출고가 정상적으로 처리된 경우, 해당 주문의 상태는 발송완료로 변경되어야 합니다.

    //
    public void ObInventory() {
        System.out.println("재고 출고를 선택하셨습니다.");
        while (true) {
            findbyOrderConfirming(); // 주문확인중상태인 주문내역을 목록으로 조회
            //    특정주문을 선택하면 주문아이템별로 출고된 내역만큼 차감처리되어야 합니다.
            System.out.print("출고할 주문번호 입력:");
            int orderId = sc.nextInt();
            sc.nextLine(); // 개행버리기
            // 선택된 주문번호의 출고처리
            boolean success = inventoryController.ObInventory(orderId);
            if (success) {
                System.out.println("출고가 완료되었습니다.");
                return;
            } else {
                System.out.println("해당 상태의 주문이 없습니다.");
                return;
            }
        }
    }

    //
    public void findbyOrderConfirming(){
        //주문확인중상태인 주문내역을 목록으로 조회할 수 있습니다.
        List<OrderDto> orders = orderController.findOrdersByStatus(Status.ORDER_CONFIRMING);
        if (orders.isEmpty()) {
            System.out.println("해당 상태의 주문이 없습니다.");
            return;
        }

        // 주문 목록 출력
        for (OrderDto order : orders) {
            System.out.println("\n주문번호: " + order.getOrderId());
            System.out.println("주문자: " + order.getOrderName());
            System.out.println("배송지: " + order.getOrderAddress());
            System.out.println("주문일: " + order.getOrderDate());
            System.out.println("상태: " + order.getStatus());
            System.out.println("주문 목록:");
//            for (int i = 0; i < order.getOrderItemList().size(); i++) {
//                OrderItemDto item = order.getOrderItemList().get(i);
//                System.out.printf("%d. %s (도서번호 %d번) %d권\n", i + 1, item.getBookdto().getTitle(),item.getBookId(), item.getQuantity() );
//            }
            for (int i = 0; i < order.getOrderItemList().size(); i++) {
                OrderItemDto item = order.getOrderItemList().get(i);
                if (item.getBookdto() != null) {
                    System.out.printf("%d. %s (도서번호 %d번) %d권\n", i + 1, item.getBookdto().getTitle(), item.getBookId(), item.getQuantity());
                } else {
                    System.out.printf("%d. (도서 정보 없음) 도서번호 %d번 %d권\n", i + 1, item.getBookId(), item.getQuantity());
                }
            }
        }
    }



}
