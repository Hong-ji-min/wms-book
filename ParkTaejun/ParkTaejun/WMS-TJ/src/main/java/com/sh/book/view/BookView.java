package com.sh.book.view;


import com.sh.book.controller.BookController;
import com.sh.book.model.entity.Book;

import java.util.Scanner;

public class BookView {
    Book book = new Book();

    BookController bookController = new BookController();
    Scanner scanner = new Scanner(System.in);


    public void bookMenuView() {

        String menu = """
                                🤗🤗🤗
                =========================================
                도서 관리 입니다. 원하시는 메뉴를 선택해주세요
                1. 도서 등록
                2. 도서 조회
                3. 도서 삭제
                4. 도서 수정 
                0. 뒤로 가기
                =========================================
                입력:""";

        while (true) {
            System.out.println(menu);
            String choice = scanner.next();
            switch (choice) {
                // 도서 등록
                case "1":
                    bookController.inserBook(insertBook());
                    break;

                // 도서 조회
                case "2":
                    bookSearch();
                    break;

                // 도서 삭제
                case "3":
                    bookController.deleteBookById(deleteBookById());
                    break;

                // 도서 수정
                case "4":
                    bookController.updateBookInfo(updateBookInfo());
                    break;

                // 뒤로 가기
                case "0":
                    return;

                default:
                    System.out.println("잘못 입력 하셨습니다. 다시 입력해 주세요.");
            }
        }


    }

    // 도서 조회 뷰
    public void bookSearch() {

        String searchMenu = """         
                =====================
                1. 전체 조회
                                
                2. 원하는 조건으로 검색
                                
                0. 뒤로 가기
                =====================
                """;

        while (true) {
            System.out.println(searchMenu);
            String choice = scanner.next();

            switch (choice) {

                case "1":
                    bookController.findAll();
                    break;


                case "2":
                    findByConditions();
                    break;


                case "0":
                    return;

                default:
                    System.out.println("잘못된 값을 입력하셨습니다. 다시 입력해 주세요.");


            }


        }

    }


    // 조건 도서 검색 뷰
    public void findByConditions() {

        String findByConditionMenu = """
                                
                ================================               
                1. 도서 아이디로 검색
                                
                2. 저자로 검색
                                
                3. 카테고리로 검색 
                                
                0. 뒤로 가기
                ================================
                """;

        while (true) {
            System.out.println(findByConditionMenu);
            String choice = scanner.next();
            switch (choice) {
                case "1":
                    System.out.println("검색하실 책의 ID를 입력해주세요.");
                    int bookId = scanner.nextInt();
                    book = bookController.findByBookId(bookId);
                    BookResultView.displayBook(book);

                    break;

                case "2":
                    System.out.println("작가 이름을 입력해주세요.");
                    String author = scanner.next();
                    bookController.findByAuthor(author);
                    break;


                case "3":
                    System.out.println("검색하실 카테고리 입력해주세요");
                    String category = scanner.next();
                    bookController.findByCategry(category);
                    break;

                case "0":
                    return;

                default:
                    System.out.println("잘못 입력하셨습니다. 다시 입력해주세요");
            }
        }


    }

    public int deleteBookById() {
        System.out.println("삭제하고 싶으신 책의 ID 입력해주세요");
        System.out.println("도서 ID 입력 : ");
        return  scanner.nextInt();


    }

    //도서 정보 업데이트
    public Book updateBookInfo() {
        System.out.println("수정하실 책의 ID를 입력해 주세요");
        int bookId = scanner.nextInt();
        System.out.println("수정 할 책의 제목을 입력하세요");
        String title = scanner.next();
        System.out.println("수정 할 책의 저자 이름을 입력하세요");
        String author = scanner.next();
        System.out.println("수정 할 책의 설명을 입력하세요");
        String description = scanner.next();
        System.out.println("수정 할 책의 가격을 입력하세요");
        int price = scanner.nextInt();
        System.out.println("수정 할 책의 카테고리명을 입력하세요");
        String category = scanner.next();
        return new Book(bookId, title, author, description, price, category, null);
    }

    // 도서 등록
    public Book insertBook() {
        System.out.println("등록하실 책의 제목 입력해주세요 !! ");
        String title = scanner.next();
        System.out.println("등록하실 저자의 이름을 입력해주세요!!");
        String author = scanner.next();
        System.out.println("등록하실 책의 설명을 간단히 입력해주세요!!");
        String description = scanner.next();
        System.out.println("등록하실 책의 가격을 입력해주세요!!");
        int price = scanner.nextInt();
        System.out.println("등록하실 책의 카테고리명을 입력해주세요!!");
        String category = scanner.next();
        return new Book(0, title, author, description, price, category, null);
    }


}
