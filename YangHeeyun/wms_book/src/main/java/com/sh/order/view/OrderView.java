package com.sh.order.view;

import com.sh.order.controller.OrderController;
import com.sh.order.model.dto.OrderDto;
import com.sh.order.model.dto.OrderItemDto;
import com.sh.order.model.dto.Status;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class OrderView {
    public static void main(String[] args) {
        new OrderView().orderMenu();
    }
    private Scanner sc = new Scanner(System.in);
    private OrderController orderController = new OrderController();

    public void orderMenu(){
        String menu = """
        =====================
        <주문 관리 메뉴 선택>
        1. 주문 생성
        2. 주문 상태별 조회
        3. 주문번호 조회
        0. 뒤로가기
        =====================
        입력 : """;

        while(true){
            System.out.print(menu);
            String choice = sc.next();
            switch(choice){
                case "1" : insertOrder(); break;
                case "2" : findOrderByStatus(); break;
                case "3" : findOrderById(); break;
                case "0" : return;
                default:
                    System.out.println("잘못 입력하셨습니다.\n");
            }
        }
    }

    private void insertOrder() {
        System.out.println("\n도서 주문서");
        System.out.println("---------------");
        System.out.print("주문자 : ");
        String ordererName = sc.next();
        sc.nextLine();
        System.out.print("배송지 : ");
        String ordererAddress = sc.nextLine();

        List<OrderItemDto> orderItemList = new ArrayList<>();


        while(true){
            System.out.print("도서 번호 입력 : ");
            int bookId = sc.nextInt();
            System.out.print("수량 입력: ");
            int quantity = sc.nextInt();

            // OrderItemDto객체 처리
            OrderItemDto orderItemDto = new OrderItemDto(0,0, bookId, quantity,null);
            orderItemList.add(orderItemDto);

            // 추가주문 여부 선택
            System.out.print("추가 주문하시겠습니까? (y/n) : ");
            if(sc.next().toLowerCase().charAt(0) != 'y')
                break;
        }

        // 주문 요청 (OrderController 메세지 전달 및 결과 확인)
        System.out.println("🎇🎇🎇주문을 요청합니다.🎇🎇🎇");
        OrderDto orderDto = new OrderDto(0, ordererName, ordererAddress, null, Status.주문확인중, orderItemList);
        System.out.println(orderDto);

        orderController.insertOrder(orderDto);
        System.out.println("주문번호 : " + orderDto.getOrderId());
    }

    private void findOrderByStatus() {
        System.out.println("주문 상태를 선택하세요 : (주문확인중/ 배송준비중/ 발송완료/ 배송완료/ 주문취소)");
        String status = sc.next();
        List<OrderDto> orderList = null;
        try {
            orderList = orderController.findOrderByStatus(Status.valueOf(status));
            OrderResultView.displayOrderByStatus(orderList);
        } catch (IllegalArgumentException e) {
            System.out.println("❌잘못된 입력입니다❌\n");
        }

//        System.out.println(orderList); // status가 null로 나옴
    }

    private void findOrderById() {
        System.out.println("\n<주문번호 조회>");
        System.out.print("> 조회할 주문번호 : ");
        int orderId = sc.nextInt();

        // OrderDto에 반환정보 담기
        OrderDto orderDto = orderController.findOrderById(orderId);
        OrderResultView.displayOrderById(orderDto);
    }
}
