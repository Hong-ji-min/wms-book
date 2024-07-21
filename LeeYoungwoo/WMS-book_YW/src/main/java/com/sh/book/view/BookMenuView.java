package com.sh.book.view;

import com.sh.book.controller.BookController;
import com.sh.book.model.dto.BookDto;
import com.sh.common.SearchCriteria;
import com.sh.user.view.MainMenuView;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class BookMenuView {
    private Scanner sc = new Scanner(System.in);
    private BookController bookController = new BookController();
    private LocalDateTime localDateTime;

    public void bookMenu(){
        String bookMenu = """
                ===================================================
                📖 도서관리 메뉴 📖
                ===================================================
                1. 도서 정보 등록
                2. 도서 정보 수정
                3. 도서 정보 삭제
                4. 도서 전체 목록 조회
                5. 도서 아이디별 목록 조회
                6. 도서 검색
                0. 돌아가기
                ===================================================
                입력 : """;
        while (true) {
            System.out.println(bookMenu);
            String choice = sc.next();
            switch (choice) {
                case "1" : bookController.insertBook(insertBook()); break;
                case "2" : bookController.updateBook(updateBook()); break;
                case "3" : bookController.deleteBook(deleteBook()); break;
                case "4" : bookController.findAll(); break;
                case "5" : bookController.findByBookId(inputBookId()); break;
                case "6" : searchMenu(); break;
                case "0" : return;
                default :
                    System.out.println("잘못 입력하셨습니다...");
            }
        }
    }

    private int deleteBook() {
        System.out.println("🖋️ 삭제하실 도서정보를 입력해주세요 🖋️");
        System.out.println("---------------------------------------------------");
        System.out.println("도서 ID 입력 : ");
        return sc.nextInt();
    }

    private BookDto updateBook() {
        System.out.println("수정하실 도서 ID 입력 : ");
        int bookId = sc.nextInt(); // 도서 ID가 잘못된 경우 검증하는 메소드가 들어가야됨
        System.out.println("🖋️ 수정하실 도서정보를 입력해주세요 🖋️");
        System.out.println("---------------------------------------------------");
        sc.nextLine();
        System.out.print("도서제목 입력 : ");
        String title = sc.nextLine();
        System.out.print("저자 입력 : ");
        String author = sc.next();
        sc.nextLine();
        System.out.print("도서 설명 입력 : ");
        String description = sc.nextLine();
        System.out.print("가격 입력 : ");
        int price = sc.nextInt();
        System.out.print("카테고리 입력 : ");
        String category = sc.next();
        return new BookDto(bookId, title, author, description, price, category, null);
    }

    private BookDto insertBook() {
        System.out.println("🖋️ 등록하실 도서정보를 입력해주세요 🖋️");
        System.out.println("---------------------------------------------------");
        sc.nextLine();
        System.out.print("도서제목 입력 : ");
        String title = sc.nextLine();
        System.out.print("저자 입력 : ");
        String author = sc.next();
        sc.nextLine();
        System.out.print("도서 설명 입력 : ");
        String description = sc.nextLine();
        System.out.print("가격 입력 : ");
        int price = sc.nextInt();
        System.out.print("카테고리 입력 : ");
        String category = sc.next();
        return new BookDto(0, title, author, description, price, category, null);
    }

    public void searchMenu() {
        String searchMenu = """
                ===================================================
                📖 도서 검색 메뉴 📖
                ===================================================
                1. 도서명 검색
                2. 저자 검색
                3. 카테고리 검색
                4. 가격 검색
                0. 돌아가기
                ===================================================
                입력 : """;
        while (true) {
            System.out.println(searchMenu);
            String choice = sc.next();
            SearchCriteria searchCriteria = switch (choice) {
                case "1" -> inputTitle();
                case "2" -> inputAuthor();
                case "3" -> inputCategory();
                case "4" -> inputPriceRange();
                default -> null;
            };
            if (searchCriteria != null) {
                bookController.searchMenu(searchCriteria);
            } else if (choice.equals("0")) {
                return;
            } else {
                System.out.println("> 잘못 입력하셨습니다.");
            }
        }
    }

    private SearchCriteria bookMenuReturn() {
        return new SearchCriteria("return", null, null, null);
    }

    private SearchCriteria inputPriceRange() {
        System.out.println("검색할 가격 범위 입력");
        System.out.println("최소값 입력 : ");
        int start = sc.nextInt();
        System.out.println("최대값 입력 : ");
        int end = sc.nextInt();
        return new SearchCriteria("price", null, start, end);
    }

    private SearchCriteria inputCategory() {
        System.out.println("검색할 카테고리 입력 : ");
        return new SearchCriteria("category", sc.next(), null, null);
    }

    private SearchCriteria inputAuthor() {
        System.out.println("검색할 저자명 입력 : ");
        return new SearchCriteria("author", sc.next(), null, null);
    }

    private SearchCriteria inputTitle() {
        System.out.println("검색할 도서명 : ");
        return new SearchCriteria("title", sc.next(), null, null);
    }

    private int inputBookId() {
        System.out.print("조회할 book_id : ");
        return sc.nextInt();
    }
}
