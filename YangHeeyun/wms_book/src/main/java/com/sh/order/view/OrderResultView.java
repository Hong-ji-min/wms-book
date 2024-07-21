package com.sh.order.view;

import com.sh.order.model.dto.OrderDto;
import com.sh.order.model.dto.OrderItemDto;

import java.util.List;

public class OrderResultView {
    public static void displayResult(String type, int result) {
        if(result > 0) {
            System.out.println("> 🎉🎉🎉" + type + " 성공 🎉🎉🎉\n");
        }
        else {
            System.out.println("> 😭😭😭" + type + " 실패 😭😭😭\n");
        }
    }

    public static void displayOrderById(OrderDto orderDto) {
        if(orderDto == null)
            System.out.println("❌주문번호가 존재하지 않습니다❌\n");
        else{
            System.out.println("\n===== 주문정보 =====");
            System.out.println("주문번호 : "+ orderDto.getOrderId());
            System.out.println("주문자 : "+ orderDto.getOrdererName());
            System.out.println("배송지 : "+ orderDto.getOrdererAddress());
            System.out.println("주문일 : "+ orderDto.getOrderDate());

            System.out.println("\n===== 주문 목록 =====");
            for(int i=0; i < orderDto.getOrderItemList().size();i++){
                OrderItemDto orderItemDto = orderDto.getOrderItemList().get(i);
                System.out.printf("%d. %s (도서번호 %d번) %d권\n",
                        i+1,orderItemDto.getBook().getTitle(),orderItemDto.getBookId(),orderItemDto.getQuantity());
            }
            System.out.println();
        }
    }

    public static void displayOrderByStatus(List<OrderDto> orderList) {
        if (orderList.isEmpty()) {
            System.out.println("❌해당상태의 주문이 존재하지 않습니다❌\n");
            return;
        } else {
            // 주문 목록 출력
            for (OrderDto orderDto : orderList) {
                System.out.println("===== 주문정보 =====");
                System.out.println("주문번호: " + orderDto.getOrderId());
                System.out.println("주문자: " + orderDto.getOrdererName());
                System.out.println("배송지: " + orderDto.getOrdererAddress());
                System.out.println("주문일: " + orderDto.getOrderDate());
                System.out.println("상태: " + orderDto.getStatus());
                System.out.println("주문 목록");
                for (int i = 0; i < orderDto.getOrderItemList().size(); i++) {
                    OrderItemDto item = orderDto.getOrderItemList().get(i);
                    System.out.printf("%d. %s (도서번호 %d번) %d권\n",
                            i + 1, item.getBook().getTitle(), item.getBookId(), item.getQuantity());
                }
                System.out.println();
            }
        }
    }
}
