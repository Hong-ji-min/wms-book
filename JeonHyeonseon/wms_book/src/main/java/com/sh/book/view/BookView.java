package com.sh.book.view;

import com.sh.book.controller.BookController;
import com.sh.book.model.dto.BookDto;
import com.sh.user.view.UserView;

import java.util.List;
import java.util.Scanner;

public class BookView {
    private BookController bookController = new BookController();
    private Scanner sc = new Scanner(System.in);

    public void bookMenu() {
        String menu = """
                    [도서 메뉴 선택]
                =====================
                     1. 도서 등록
                     2. 도서 수정
                     3. 도서 삭제
                     4. 도서 조회
                     0. 뒤로가기
                =====================
                입력 : """;
        while (true) {
            System.out.print(menu);
            String choice = sc.next();
            switch (choice) {
                case "1" :
                    insertBook();
                    break;
                case "2" :
                    updateBook();
                    break;
                case "3" :
                    deleteBook();
                    break;
                case "4" :
                    displayBookMenu();
                    break;
                case "0" : return;
                default:
                    System.out.println("잘못 입력하셨습니다. 다시 입력해주세요.");
            }
        }
    }

    private void deleteBook() {
        System.out.println("     [ 도서 삭제 ]");
        System.out.println("---------------------");
        System.out.print("도서 아이디 : ");
        int bookId = sc.nextInt();
        bookController.deleteBook(bookId);
    }

    private void updateBook() {
        System.out.println("     [ 도서 수정 ]");
        System.out.println("---------------------");
        System.out.println("수정할 도서 아이디를 입력하세요.");
        System.out.println("도서 아이디 : ");
        int bookId = sc.nextInt();
        sc.nextLine();
        System.out.print("도서명 : ");
        String title = sc.nextLine();
        System.out.print("저자 : ");
        String author = sc.nextLine();
        System.out.print("설명 : ");
        String description = sc.nextLine();
        System.out.print("가격 : ");
        int price = sc.nextInt();
        sc.nextLine();
        System.out.print("카테고리 : ");
        String category = sc.nextLine();
//        System.out.println(bookId + " " + title + " " + author+ " "  + description+ " "  + price+ " "  + category);
        BookDto bookDto = new BookDto(bookId, title, author, description, price, category, null);
        bookController.updateBook(bookDto);
    }

    private void insertBook() {
        System.out.println("     [ 도서 등록 ]");
        System.out.println("---------------------");
        sc.nextLine(); // 초기화
        System.out.print("도서명 : ");
        String title = sc.nextLine();
        System.out.print("저자 : ");
        String author = sc.nextLine();
        System.out.print("설명 : ");
        String description = sc.nextLine();
        System.out.print("가격 : ");
        int price = sc.nextInt();
        System.out.print("카테고리 : ");
        String category = sc.next();
        BookDto bookDto = new BookDto(0, title, author, description, price, category, null);
        bookController.insertBook(bookDto);
    }

    // 도서 조회 및 검색
    private void displayBookMenu() {
        String menu = """
                    [도서 조회/검색]
                =====================
                1. 전체 도서 조회
                2. 도서 아이디로 조회
                3. 도서 조건 검색
                0. 뒤로 가기
                =====================
                입력 : """;
        while (true) {
            System.out.print(menu);
            String choice = sc.next();
            switch (choice) {
                case "1" :
                    bookController.findAllBooks();
                    break;
                case "2" :
                    inputBookId();
                    break;
                case "3" :
                    inputFilterBook();
                    break;
                case "0" : bookMenu();
                default:
                    System.out.println("잘못 입력하셨습니다. 다시 입력해주세요.");
            }
        }
    }

    // 도서 조건검색
    private void inputFilterBook() {
        String menu = """
                    [도서 조건검색]
                =====================
                    1. 도서명 검색
                    2. 저자 검색
                    3. 카테고리 검색
                    4. 가격 검색
                    0. 뒤로 가기
                =====================
                입력 : """;
        System.out.print(menu);
        String choice = sc.next();
        switch (choice) {
            case "1" -> inputTitle();
            case "2" -> inputAuthor();
            case "3" -> inputCategory();
            case "4" -> inputPrice();
            case "0" -> bookMenu();
            default -> {
                return;
            }
        };
    }

    private void inputPrice() {
        System.out.print("가격 : ");
        int price = sc.nextInt();
        sc.nextLine();
        bookController.findByPrice(price);
    }

    private void inputCategory() {
        System.out.print("카테고리 : ");
        String category = sc.next();
        sc.nextLine();
        bookController.findByCategory(category);
    }

    private void inputAuthor() {
        System.out.print("저자 : ");
        String author = sc.next();
        sc.nextLine();
        bookController.findByAuthor(author);
    }

    private void inputTitle() {
        System.out.print("제목 : ");
        String title = sc.next();
        sc.nextLine();
        bookController.findByTitle(title);
    }

    private void inputBookId() {
        System.out.print("도서 아이디 조회 : ");
        int bookId = sc.nextInt();
        bookController.findByBookId(bookId);
    }

}
