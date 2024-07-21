package com.sh.order.view;

import com.sh.order.model.entity.Order;
import com.sh.order.model.entity.OrderItem;

public class OrderResultView {
    public static void displayCreateOrder(int result) {


        if (result > 0) {
            System.out.println("\t🤗주문 처리 성공🤗\t");
        }
    }

    public static void findOrder(Order order) {
        System.out.println("=====주문정보======");
        System.out.println("주문번호 : " + order.getOrderId());
        System.out.println("주문자 : " + order.getOrderName());
        System.out.println("배송지 : " + order.getOrderAddress());
        System.out.println("주문일 : " + order.getOrderDate());

        System.out.println("=====주문 목록======");
        int index = 1;
        for (OrderItem orderItem : order.getOrderItems()) {

            System.out.println( index++ +". 책이름 : " + orderItem.getBook().getTitle()) ;
        }
        System.out.println();

    }
}
