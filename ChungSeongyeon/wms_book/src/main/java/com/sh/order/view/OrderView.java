package com.sh.order.view;

import com.sh.order.controller.OrderController;
import com.sh.order.model.dto.OrderDto;
import com.sh.order.model.dto.OrderItemDto;
import com.sh.order.model.dto.OrderableStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class OrderView {
    private final Scanner sc = new Scanner(System.in);
    private final OrderController orderController = new OrderController();

    public void orderMenu() {
        String orderMenu = """
                [주문 관리 메뉴 선택]
                1. 주문 생성
                2. 주문 상태별 조회
                3. 주문 상세 조회
                0. 뒤로 가기
                """;
        while (true) {
            System.out.println(orderMenu);
            String choice = sc.next();
            switch (choice) {
                case "1":
                    createOrder();
                    break;
                case "2":
                    searchOrdersByStatus();
                    break;
                case "3":
                    searchOrderById();
                    break;
                case "0":
                    return;
                default:
                    System.out.println("❌잘못된 입력.. 다시 입력해주세요.❌");
            }
        }
    }

    private void createOrder() {
        System.out.println("--[주문 생성]--");
        System.out.print("> 주문자 이름: ");
        sc.nextLine();
        String ordererName = sc.nextLine();
        System.out.print("> 배송지: ");
        String ordererAddress = sc.nextLine();

        List<OrderItemDto> orderItems = new ArrayList<>();

        while (true) {
            System.out.print("> 도서 번호: ");
            int bookId = sc.nextInt();
            System.out.print("> 수량: ");
            int quantity = sc.nextInt();
            sc.nextLine();

            orderItems.add(new OrderItemDto(0, 0, bookId, quantity));

            System.out.print("> 더 추가하시겠습니까? (y/n): ");
            String choice = sc.nextLine();
            if ("n".equalsIgnoreCase(choice)) {
                break;
            }
        }

        OrderDto order = new OrderDto(0, ordererName, ordererAddress, null, OrderableStatus.주문확인중, orderItems);
        orderController.registerOrder(order);

        System.out.println("✅ 주문이 등록되었습니다.");
    }

    private void searchOrdersByStatus() {
        System.out.println("--[주문 상태별 조회]--");
        System.out.println("주문 상태를 선택하세요: ");
        for (OrderableStatus status : OrderableStatus.values()) {
            System.out.println(status);
        }
        sc.nextLine();
        String statusInput = sc.nextLine();
        try {
            OrderableStatus status = OrderableStatus.valueOf(statusInput);
            List<OrderDto> orders = orderController.getOrdersByStatus(status);
            orders.forEach(order -> {
                System.out.println(order);
                order.getOrderItems().forEach(item -> System.out.println("  " + item));
            });
        } catch (IllegalArgumentException e) {
            System.out.println("❌잘못된 주문 상태입니다.❌");
        }
    }

    private void searchOrderById() {
        System.out.println("--[주문 상세 조회]--");
        System.out.print("> 주문 번호: ");
        int orderId = sc.nextInt();
        sc.nextLine();

        OrderDto order = orderController.getOrderById(orderId);
        if (order != null) {
            System.out.println(order);
            order.getOrderItems().forEach(item -> System.out.println("  " + item));
        } else {
            System.out.println("❌주문을 찾을 수 없습니다.❌");
        }
    }
}
