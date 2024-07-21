package com.sh.order.view;



import com.sh.book.controller.BookController;
import com.sh.book.model.entity.Book;
import com.sh.order.controller.OrderController;
import com.sh.order.model.entity.Order;
import com.sh.order.model.entity.OrderItem;
import com.sh.order.model.entity.Status;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class OrderView {
    OrderController orderController = new OrderController();
    BookController bookController = new BookController();
    Book book = new Book();
    Scanner scanner = new Scanner(System.in);


    public void orderManageView() {
        Scanner scanner = new Scanner(System.in);


        String orderMenu = """
                ===================================================
                주문 관리 시스템 입니다. 원하시는 뉴를 선택해주세요.
                1. 주문 생성(요청된 주문에 대한)
                2. 주문 조회 
                0. 뒤로 가기
                ===================================================
                 입력:""";

        while (true) {
            System.out.println(orderMenu);
            String choice = scanner.next();
            switch (choice) {
                // 주문생성
                case "1":
                    makeOrder();
                    break;


                //주문 처리
                case "2":
                    findOrder();
                    break;

                //뒤로 가기
                case "0":
                    return;


                default:
                    System.out.println("잘못된 입력입니다. 다시 입력해주세요");
            }
        }

    }


    // 주문서에 기재되어 있는 값 입력
    public void makeOrder() {
        List<OrderItem> orderList = new ArrayList<>();
        System.out.println("🔥고객으로부터 요청된 도서 주문서 입니다🔥\n");
        System.out.println("===================================");
        System.out.println("🔥주문하시는 사용자의 성함을 입력해주세요🔥");
        String orderName = scanner.next();
        System.out.println("🔥주문하시는 사용자의 주소를 입력해주세요🔥");
        String orderaddress = scanner.next();

        System.out.println("🍚주문내역을 도서별로 입력하세요.🍚\n");
        System.out.println("===================================");
        while (true) {
            System.out.println("🚓주문 요청 받은 책의 ID를 입력해주세요🚓");
            int bookId = scanner.nextInt();
            book = bookController.findByBookId(bookId);
            if (book == null) {
                System.out.println("해당 도서가 존재하지 안습니다. 다시 입력해주세요.");
                continue;
            }
            // 수량 선택
            System.out.println("📕주문 요청 받은 책의 수량을 입력해주세요 : 📕");
            int quantity = scanner.nextInt();

            OrderItem orderItem = new OrderItem(0, 0, bookId, quantity, null, null);
            orderList.add(orderItem);

            // 주문 추가
            System.out.println(" 🤔요청받은 추가 주문이 있나요?🤔  (y/n) :\n");
            if (scanner.next().toLowerCase().charAt(0) != 'y')
                break;

        }


        // 주문 사용자 입력 정보 입력 및 요청
        System.out.println(" 🔥요청 된 주문이 등록되었습니다.🔥 ");
        System.out.println();

        Order order = new Order(0, orderName, orderaddress, null, Status.발송완료, orderList);
        orderController.createOrder(order);

        System.out.println("-----------------------------");
        System.out.println("주문자 : " + orderName);
        System.out.println("배송지 : " + orderaddress);
        System.out.println("주문일 : " + LocalDate.now());

    }

    public void findOrder(){

        while (true){
            System.out.println("확인 하고 싶으신 OrderId를 입력해주세요");
            int orderId = scanner.nextInt();
            Order order = orderController.findOrder(orderId);
            OrderResultView.findOrder(order);
        }

    }




}