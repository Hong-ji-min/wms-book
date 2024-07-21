package com.sh.order.view;

import com.sh.order.model.dto.OrderDto;
import com.sh.order_item.model.dto.OrderItemDto;

import java.util.List;

public class OrderResultView {
    public static void displayOrderById(OrderDto order) {
        if(order == null)
            System.out.println("주문번호가 존재하지 않습니다\n");
        else{
            System.out.println("\n===== 주문정보 =====");
            System.out.println("주문번호 : "+ order.getOrderId());
            System.out.println("주문자 : "+ order.getOrdererName());
            System.out.println("배송지 : "+ order.getOrdererAddress());
            System.out.println("주문일 : "+ order.getOrderDate());

            System.out.println("\n===== 주문 목록 =====");
            for(int i=0; i < order.getOrderItemList().size();i++){
                OrderItemDto orderItemDto = order.getOrderItemList().get(i);
                System.out.printf("%d. %s (도서번호 %d번) %d권\n",
                        i+1,orderItemDto.getBook().getTitle(),orderItemDto.getBookId(),orderItemDto.getQuantity());
            }
            System.out.println();
        }
    }

    public static void displayOrderByStatus(List<OrderDto> orderList) {
        if (orderList.isEmpty()) {
            System.out.println("주문이 존재하지 않습니다.\n");
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