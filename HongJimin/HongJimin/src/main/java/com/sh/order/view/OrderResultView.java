package com.sh.order.view;

import com.sh.order.model.entity.Order;

public class OrderResultView {
    public static void displayResult(String type, int result) {
        System.out.println("📢📢📢 " + type + (result > 0 ? "성공! 😊" : "실패 😂") + " 📢📢📢");
    }

    public static void displayOrderInfo(Order order) {
        System.out.println("""
                ===================
                  ❇️ 도서 주문서 ❇️
                ===================""");
        System.out.printf("주문자 : %s \n배송지 : %s\n 주문일 : %s", order.getOrdererName(), order.getOrdererAddress(), order.getOrderDate());
    }
//    public static void displayBookOrder(Order order) {
//        System.out.println("""
//                ======================
//                  ❇️ 도서 주문 목록 ❇️
//                ======================
//                  """);
//        for(int i = 0; i < order.getOrderItemList().toArray().length; i++) {
//            System.out.printf("%d. %s (도서번호 : %d번) %d권",
//                    (i+1),
//                    order.getOrderItemList().indexOf(i));
//        }
}
