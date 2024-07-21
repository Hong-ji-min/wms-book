package com.sh.book.view;

import com.sh.book.controller.BookController;
import com.sh.book.model.dto.BookDto;

import java.util.List;
import java.util.Scanner;

public class BookView {
    private Scanner sc = new Scanner(System.in);
    private BookController bookController = new BookController();

    public void bookMenu() {
        String bookMenu = """
                [도서 관리 메뉴 선택]
                 1. 도서 정보 등록
                 2. 도서 정보 수정
                 3. 도서 정보 삭제
                 4. 도서 목록 조회 및 검색
                 0. 뒤로 가기
                """;
        while (true) {
            System.out.println(bookMenu);
            String choice = sc.next();
            switch (choice) {
                case "1":
                    insertBook();
                    break;
                case "2":
                    updateBook();
                    break;
                case "3":
                    deleteBook();
                    break;
                case "4":
                    displayBookMenu();
                    break;
                case "0":
                    return;
                default:
                    System.out.println("❌잘못된 입력.. 다시 입력해주세요.❌");
            }
        }
    }

    private void displayBookMenu() {
        String menu = """
                =================
                1. 전체 도서 조회
                2. 도서 아이디로 조회
                3. 도서 검색
                0. 뒤로 가기
                =================
                """;
        while (true) {
            System.out.println(menu);
            String choice = sc.next();
            switch (choice) {
                case "1":
                    displayAllBooks();
                    break;
                case "2":
                    displayBookById();
                    break;
                case "3":
                    searchBooks();
                    break;
                case "0":
                    return;
                default:
                    System.out.println("❌잘못된 입력입니다.❌");
            }
        }
    }

    private void displayAllBooks() {
        List<BookDto> books = bookController.getAllBooks();
        for (BookDto book : books) {
            System.out.println(book);
        }
    }

    private void displayBookById() {
        System.out.print("> 도서 아이디 입력: ");
        int bookId = sc.nextInt();
        BookDto book = bookController.getBookById(bookId);
        if (book != null) {
            System.out.println(book);
        } else {
            System.out.println("❌도서를 찾을 수 없습니다.❌");
        }
    }

    private void searchBooks() {
        System.out.println("> 검색 기준을 선택하세요:");
        System.out.println("1. 도서명");
        System.out.println("2. 저자");
        System.out.println("3. 카테고리");
        System.out.println("4. 가격");
        System.out.print("> 선택: ");

        int option = sc.nextInt();
        String title = null;
        String author = null;
        String category = null;
        Integer price = null;

        switch (option) {
            case 1:
                System.out.print("> 도서명 입력: ");
                title = sc.next();
                break;
            case 2:
                System.out.print("> 저자 입력: ");
                author = sc.next();
                break;
            case 3:
                System.out.print("> 카테고리 입력: ");
                category = sc.next();
                break;
            case 4:
                System.out.print("> 가격 입력: ");
                price = sc.nextInt();
                break;
            default:
                System.out.println("❌올바른 옵션을 선택하세요.❌");
                return;
        }

        List<BookDto> books = bookController.searchBookByCriteria(title, author, category, price);
        for (BookDto book : books) {
            System.out.println(book);
        }
    }

    private void insertBook() {
        System.out.println("--[도서 등록]--");
        System.out.print("> 도서명 입력 : ");
        sc.nextLine(); // Consume newline
        String title = sc.nextLine();
        System.out.print("> 저자 입력 : ");
        String author = sc.next();
        sc.nextLine(); // Consume newline
        System.out.print("> 설명 입력 : ");
        String description = sc.nextLine();
        System.out.print("> 가격 입력 :");
        int price = sc.nextInt();
        sc.nextLine(); // Consume newline
        System.out.print("> 카테고리 입력 : ");
        String category = sc.next();
        BookDto book = new BookDto(0, title, author, description, price, category, null);
        bookController.insertBook(book);
    }

    private void updateBook() {
        System.out.println("--[도서 정보 수정]--");
        System.out.print("> 수정할 도서 ID 입력  : ");
        int bookId = sc.nextInt();
        sc.nextLine(); // Consume newline
        System.out.print("> 도서명 입력 : ");
        String title = sc.next();
        sc.nextLine(); // Consume newline
        System.out.print("> 저자 입력 : ");
        String author = sc.next();
        sc.nextLine(); // Consume newline
        System.out.print("> 설명 입력 : ");
        String description = sc.nextLine();
        System.out.print("> 가격 입력 :");
        int price = sc.nextInt();
        sc.nextLine(); // Consume newline
        System.out.print("> 카테고리 입력 : ");
        String category = sc.next();
        BookDto book = new BookDto(bookId, title, author, description, price, category, null);
        bookController.updateBook(book);
    }

    private void deleteBook() {
        System.out.println("--[도서 정보 삭제]--");
        System.out.print("> 삭제할 도서 아이디 입력: ");
        int bookId = sc.nextInt();
        bookController.deleteBook(bookId);
        System.out.println("✅ 도서가 삭제되었습니다.");
    }
}
