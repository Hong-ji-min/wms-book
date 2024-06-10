package com.sh.order.view;

import com.sh.book.controller.BookController;
import com.sh.order.controller.OrderController;
import com.sh.order.model.entity.Order;
import com.sh.order.model.entity.OrderItem;
import com.sh.order.model.entity.Status;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class OrderView {
    Scanner sc = new Scanner(System.in);
    OrderController orderController = new OrderController();
    BookController bookController = new BookController();
    List<OrderItem> orderItemList = new ArrayList<>();

    public void orderMenu() {

        String orderMenu = """
                =============================
                   📜 주문 관리 메뉴입니다. 📜
                =============================
                      1. 주문 상태로 조회
                      2. 주문 ID로 조회
                      3. 주문서 작성
                =============================
                """;
        System.out.println(orderMenu);
        int choice = sc.nextInt();
        while (true) {
            switch (choice) {
                case 1:
                    findOrder();
                    break;
                case 2:
                    findOrderByOrderID();
                    break;
                case 3:
                    orderBook();
                    break;
                default:
                    System.out.println("잘못된 입력입니다. 다시 입력해주세요");
            }
        }
    }

    private void findOrderByOrderID() {
        while(true) {
        System.out.print("▶️ 주문 ID를 입력해주세요 : ");
        orderController.findOrderByOrderId(sc.nextInt());

        System.out.print("주문 조회를 더 하시겠습니까? (y/n) : ");
        if (sc.next().toLowerCase().charAt(0) != 'y')
            break;
        }


    }

    public void findOrder() {
        String findOrderStatus = """
                ================================
                 📜조회할 주문 상태를 선택해주세요📜
                ================================
                        1. 주문확인중
                        2. 배송준비중
                        3. 발송완료
                        4. 배송중
                        5. 배송완료
                        6. 주문취소
                        7. 뒤로가기
                ================================
                """;
        while (true) {
            System.out.println(findOrderStatus);
            int choice = sc.nextInt();
            switch (choice) {
                case 1:
                    orderController.findByOrderStatus(Status.주문확인중);
                    break;
                case 2:
                    orderController.findByOrderStatus(Status.배송준비중);
                    break;
                case 3:
                    orderController.findByOrderStatus(Status.발송완료);
                    break;
                case 4:
                    orderController.findByOrderStatus(Status.배송중);
                    break;
                case 5:
                    orderController.findByOrderStatus(Status.배송완료);
                    break;
                case 6:
                    orderController.findByOrderStatus(Status.주문취소);
                    break;
                case 7:
                    return;
                default:
                    System.out.println("잘못된 입력입니다. 다시 입력해주세요.");
            }
        }
    }

    public void orderBook() {
        while (true) {
            //도서 리스트 출력
            bookController.findAllBook();

            String orderBooks = """
                    ==============================
                       📑 도서 주문서를 작성합니다. 📑
                    ==============================""";
            System.out.println(orderBooks);

            System.out.print("▶ 도서 아이디 입력 : ");
            int bookId = sc.nextInt();

            // 선택한 도서 아이디의 책 정보 출력
            bookController.findBookByBookId(bookId);

            // 수량 선택
            System.out.print("▶ 수량 입력 : ");
            int amount = sc.nextInt();

            OrderItem orderItem = new OrderItem(bookId, amount);
            orderItemList.add(orderItem);

            System.out.print("도서를 추가 주문 하시겠습니까? (y/n) : ");
            if (sc.next().toLowerCase().charAt(0) != 'y')
                break;
        }

        //주문자 정보 입력
        System.out.println("▶ 주문자 이름 입력 : ");
        String name = sc.next();
        sc.nextLine();
        System.out.println("▶ 배송지 입력 : ");
        String address = sc.nextLine();

        System.out.println("📖 도서 주문 등록합니다.📖");
        Order order = new Order(0, name, address, LocalDateTime.now(), Status.주문확인중, orderItemList);

        orderController.createOrder(order);
    }

}


