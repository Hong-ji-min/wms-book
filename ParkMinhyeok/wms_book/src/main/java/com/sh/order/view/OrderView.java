package com.sh.order.view;

import com.sh.book.controller.BookController;
import com.sh.book.model.dto.BookDto;
import com.sh.book.model.service.BookService;
import com.sh.order.controller.OrderController;
import com.sh.order.model.dto.OrderDto;
import com.sh.order.model.entity.Status;
import com.sh.order_item.model.dto.OrderItemDto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class OrderView {
    private BookService bookService = new BookService();
    private BookController bookController = new BookController();
    private OrderController orderController = new OrderController();
    Scanner sc = new Scanner(System.in);

    public void orderMenu() {
        String menu =
                """
                        ===========================================
                        메뉴를 선택해주세요.
                        1. 주문 생성
                        2. 주문 처리
                        3. 주문 상태별 조회
                        4. 주문 ID로 조회
                        0. 뒤로가기
                        ===========================================
                        """;
        System.out.println(menu);
        String choice = sc.next();
        switch (choice) {
            case "1":
                insertOrderMenu();
                break;
            case "2":
                System.out.println("주문 처리");
                break;
            case "3":
                findOrderByStatusMenu();
                break;
            case "4":
                findOrderByIdMenu();
                break;
            case "0":
                return;
            default:
                System.out.println("잘못된 입력입니다.");
        }
    }

    private void findOrderByIdMenu() {
        System.out.println("주문 id를 입력해주세요.");
        int id = sc.nextInt();
        OrderDto order = orderController.findOrderById(id);
        OrderResultView.displayOrderById(order);
    }

    private void findOrderByStatusMenu() {
        System.out.println("주문 상태를 입력해주세요. (주문확인중/배송준비중/발송롼료/배송중/배송완료/주문취소)");
        String status = sc.next();
        List<OrderDto> orderByStatusList = null;
        try {
            orderByStatusList = orderController.findOrderByStatus(Status.valueOf(status));
            OrderResultView.displayOrderByStatus(orderByStatusList);
        } catch (IllegalArgumentException e) {
            System.out.println("잘못된 입력입니다.");
        }
    }

    public void insertOrderMenu() {
        System.out.println("주문을 시작합니다.");

        // 주문 객체에 포함된 변수 준비
        List<OrderItemDto> orderItemList = new ArrayList<>();

        // 반복처리
        // 1. 주문내역을 OrderItemDto객체로 생성
        // 2. OrderItemList에 추가
        while (true) {
            // 책 노출
            bookController.findAll();
            System.out.println("책 카테고리를 입력해주세요.");
            String category = sc.next();

            // 선택한 카테고리 메뉴 노출
            bookController.findByCategory(category);
            List<BookDto> bookList = bookService.findByCategory(category);
            if (bookList.isEmpty())
                continue;

            // 책 선택
            System.out.println("책 ID를 입력해주세요.");
            int bookId = sc.nextInt();

            // 수량 선택
            System.out.println("수량을 입력해주세요.");
            int quantity = sc.nextInt();

            // orderItem 객체 처리
            OrderItemDto orderItem = new OrderItemDto(0, 0, bookId, quantity, null);
            orderItemList.add(orderItem);

            // 추가 주문 여부
            System.out.println("추가 주문하시겠습니까? (y/n)");
            if (sc.next().toLowerCase().charAt(0) != 'y')
                break;
        }
        // 주문 요청
        System.out.println("주문을 요청합니다");
        System.out.println("주문자의 성함을 입력해주세요");
        String ordererName = sc.next();
        sc.nextLine(); // 공백을 읽어버리는 nextLine 문제로 넣음
        System.out.println("주소를 입력해주세요. ex) 서울시 강남구");
        String ordererAddress = sc.nextLine();
        OrderDto order = new OrderDto(0, ordererName, ordererAddress, LocalDateTime.now(), Status.주문확인중, orderItemList);
        System.out.println(order);

        orderController.createOrder(order);
        System.out.println("주문 ID : " + order.getOrderId());

        System.out.println("주문 정상 종료.");
    }
}

