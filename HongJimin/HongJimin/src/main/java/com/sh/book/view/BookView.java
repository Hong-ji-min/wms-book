package com.sh.book.view;

import com.sh.book.controller.BookController;

import java.util.Scanner;

public class BookView {
    Scanner sc = new Scanner(System.in);
    BookController bookController = new BookController();

    public void bookMenu() {
        String bookMenu = """
                =============================
                  📖도서 관리 메뉴입니다 :-)📖
                =============================
                        1. 도서 등록
                        2. 도서 조회
                        3. 도서 수정
                        4. 도서 삭제
                        5. 뒤로 가기
                =============================
                """;
        System.out.print(bookMenu);
        String choice = sc.next();
        switch (choice) {
            case "1" :
                // 1. 도서 등록
                bookController.insertBook();
                break;
            case "2" :
                // 2. 도서 조회
                findBookMenu();
                break;
            case "3" :
                // 3. 도서 수정
                bookController.updateBook();
                break;
            case "4" :
                // 4. 도서 삭제
                bookController.deleteBook();
                break;
            case "5" :
                // 5. 뒤로 가기
                return;
            default:
                System.out.println("잘못된 입력입니다. 다시 입력해주세요😥");
        }
    }

    public void findBookMenu(){
        String findBookMenu = """
                ===============================
                📖 도서 조회 메뉴를 선택해주세요 📖
                ===============================
                    1. 도서 전체 목록 조회
                    2. BookId로 도서 1권 조회
                    3. 분류 별 조회
                    4. 뒤로 가기
                ===============================
                """;
        System.out.print(findBookMenu);
        String choice = sc.next();
        switch (choice) {
            case "1" : bookController.findAllBook(); break;
            case "2" : findBookById(); break;
            case "3" : findBookByCategory(); break;
            case "4" : return;
            default:
                System.out.println("잘못된 입력입니다. 다시 입력해주세요.");
        }
    }


    public void findBookById(){
        System.out.println("▶️ Book Id 입력 : ");
        int bookId = sc.nextInt();
        bookController.findBookByBookId(bookId);
    }

    private void findBookByCategory() {
        String menu = """
                ===============================
                  📖 조회할 메뉴를 선택해주세요 📖
                ===============================
                        1. 도서명으로 검색
                        2. 저자명으로 검색
                        3. 카테고리로 검색
                        4. 가격으로 검색
                        5. 뒤로 가기
                ===============================
                """;
        System.out.println(menu);
        int choice = sc.nextInt();
        switch (choice) {
            case 1 :
                System.out.print("▶ 도서 명을 입력해주세요 : ");
                String title = sc.next();
                bookController.findByTitle(title); break;
            case 2 :
                System.out.print("▶ 저자를 입력해주세요 : ");
                String author = sc.next();
                bookController.findByAuthor(author); break;
            case 3 :
                System.out.print("▶ 카테고리를 입력해주세요 : ");
                String category = sc.next();
                bookController.findByCategory(category); break;
            case 4 :
                System.out.print("▶ 가격을 입력해주세요 ");
                int price = sc.nextInt();
                bookController.findBookByPrice(price); break;
            case 5 :
                return;
            default:
                System.out.println("잘못된 입력입니다. 다시 입력해주세요");
        }
    }
}
