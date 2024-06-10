package com.sh.order.view;

import com.sh.order.model.entity.Order;
import com.sh.order.model.entity.OrderItem;
import com.sh.order.model.entity.Status;

import java.time.format.DateTimeFormatter;
import java.util.List;

public class OrderResultView {
    public static void displayResult(String type, int result) {
        System.out.println("📢📢📢 " + type + (result > 0 ? "성공! 😊" : "실패 😂") + " 📢📢📢");
    }

    public static void displayOrderInfo(Order order) {
        System.out.println("""
                ===================
                  🛎️ 도서 주문서 🛎️
                ===================""");
        System.out.printf("주문자 : %s \n배송지 : %s \n주문일 : %s\n", order.getOrdererName(), order.getOrdererAddress(), order.getOrderDate().format(DateTimeFormatter.ofPattern("yyyy년 MM월 dd일")));

        System.out.println("""
                ======================
                  ❇️ 도서 주문 목록 ❇️
                ======================""");
        int i = 1;
        for (OrderItem orderItem : order.getOrderItemList()) {
            System.out.printf("%d. %s (도서번호 : %d번) %d권\n",
                    i++,
                    orderItem.getBook().getTitle(),
                    orderItem.getBook().getBookId(),
                    orderItem.getQuantity());
        }
    }

    public static void displayOrderStatus(List<Order> orderList, Status status) {
        if (orderList.isEmpty()) {
            System.out.println(status + "상태 도서 목록은 존재하지 않습니다.");
        } else {
            System.out.println("=========================================");
            System.out.printf(" 📜주문 상태 = [%s] 도서 목록입니다📜\n", status);
            System.out.println("=========================================");

            for (int i = 0; i < orderList.toArray().length; i++) {
                System.out.printf("🪪 주문자 : %s\n", orderList.get(i).getOrdererName());
                for (int j = 0; j < orderList.get(i).getOrderItemList().toArray().length; j++) {
                    System.out.printf("%d. %s (도서번호 %d번) %d권\n",
                            (j + 1),
                            orderList.get(i).getOrderItemList().get(j).getBook().getTitle(),
                            orderList.get(i).getOrderItemList().get(j).getBook().getBookId(),
                            orderList.get(i).getOrderItemList().get(j).getQuantity());
                }
                System.out.println("-----------------------------------------");
            }
        }
    }

    public static void displayOrderById(Order order) {
        String orderInfo = """
                ==============================
                           주문 정보
                ==============================""";
        System.out.println(orderInfo);
        System.out.println("주문 번호 : " + order.getOrderId() + "\n" +
                "주문자 : " +  order.getOrdererName() + "\n" +
                "배송지 : " + order.getOrdererAddress() + "\n" +
                "주문일 : " + order.getOrderDate().format(DateTimeFormatter.ofPattern("yyyy년 MM월 dd일")));

        String orderList = """
                ========================================
                                주문 목록
                ========================================""";
        System.out.println(orderList);
        int i = 1;
        for(OrderItem orderItem : order.getOrderItemList()) {
            System.out.printf("%d. %s (도서번호 : %d번) %d권\n",
                    i++,
                    orderItem.getBook().getTitle(),
                    orderItem.getBook().getBookId(),
                    orderItem.getQuantity());
        }
    }
}
