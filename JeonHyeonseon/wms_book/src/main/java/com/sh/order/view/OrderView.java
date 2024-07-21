package com.sh.order.view;

import com.sh.book.controller.BookController;
import com.sh.order.controller.OrderController;
import com.sh.order.model.dto.OrderDto;
import com.sh.order.model.dto.OrderItemDto;
import com.sh.order.model.dto.Status;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class OrderView {
    private OrderController orderController = new OrderController();

    private Scanner sc = new Scanner(System.in);

    public void orderMenu() {
        String menu = """
                    [주문 메뉴 선택]
                =====================
                     1. 주문 생성
                     2. 주문 상태
                     0. 돌아가기
                =====================
                입력 : """;
        System.out.print(menu);
        String choice = sc.next();
        switch (choice) {
            case "1" :
                inputOrderBook();
                break;
            case "2" : break;
            case "0" : return;
            default:
                System.out.println("잘못 입력하셨습니다. 다시 입력해주세요.");
        }
    }
    private void inputOrderBook() {
        System.out.println("👉 주문할 도서를 입력해주세요");
        System.out.println("  [ 주문자 정보 입력 ]");
        System.out.println("---------------------");
        System.out.print("이름 : ");
        String ordererName = sc.nextLine();
        sc.nextLine();
        System.out.print("주소 : ");
        String ordererAddress = sc.nextLine();

        List<OrderItemDto> orderItemList = new ArrayList<>();

        while (true) {
            // 도서 아이디 선택
            System.out.print("도서 아이디 : ");
            int bookId = sc.nextInt();

            // 수량 선택
            System.out.print("수량 : ");
            int quantity = sc.nextInt();

            // OrderItem객체 처리
            orderItemList.add(new OrderItemDto(0, 0, bookId, quantity));

            // 추가 주문 여부
            System.out.print("추가적으로 주문하시겠습니까? (y/n) : ");
            if(sc.next().toUpperCase().charAt(0) != 'y') {
                break;
            }
        }
        // 주문요청 (OrderController 메시지 전달)
        OrderDto orderDto = new OrderDto(0, ordererName,ordererAddress, null, Status.주문확인중, orderItemList);
        orderController.createOrder(orderDto);
    }
}
