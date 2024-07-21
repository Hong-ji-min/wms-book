package com.sh.book.view;

import com.sh.book.model.entity.Book;

import java.util.List;

public class BookResultView {

    public static void displayBookList(List<Book> list) {
        if (list.isEmpty()) {
            System.out.println("> 조회된 메뉴가 없습니다. ");
        } else {
            System.out.println("=================================================================");
            System.out.printf("%s\t%s\t%s\t%s\t%s\n", "--도서 제목--", "--저자--", "--설명--", "--가격--", "--카테고리--");
            System.out.println("=================================================================");
            for (Book book : list) {
                System.out.printf("%d\t%s\t%s\t%s\t%d\t%s\n",
                        book.getBookId(),
                        book.getTitle(),
                        book.getAuthor(),
                        book.getDescription(),
                        book.getPrice(),
                        book.getCategory(),
                        book.getCreatedAt()
                );
            }
        }
    }

    public static void displayBook(Book book) {

        if (book == null) {
            System.out.println("> 조회된 메뉴가 없습니다. ");
        } else {
            System.out.println("=================================================================");
            System.out.println(
                    "제목 : " + book.getTitle() + "\n" +
                            "저자 : " + book.getAuthor() + "\n" +
                            "책 ID : " + book.getBookId() + "\n" +
                            "책 설명 : " + book.getDescription() + "\n" +
                            "책 가격 : " + book.getPrice() + "\n" +
                            "책 카테고리 : " + book.getCategory());
            System.out.println("=================================================================");
        }
    }

    public static void displayBookByAuthor(List<Book> list) {
        if (list.isEmpty()) {
            System.out.println("> 조회된 메뉴가 없습니다. ");
        } else {
            System.out.println("=================================================================");
            System.out.printf("%s\t%s\t%s\t%s\t%s\n", "--도서 제목--", "--저자--", "--설명--", "--가격--", "--카테고리--");
            System.out.println("=================================================================");
            for (Book book : list) {
                System.out.printf("%s\t%s\t%s\t%d\t%s\n",
                        book.getTitle(),
                        book.getAuthor(),
                        book.getDescription(),
                        book.getPrice(),
                        book.getCategory());
            }
        }
    }

    public static void displayBookByCategory(List<Book> list) {
        if (list.isEmpty()) {
            System.out.println("> 조회된 메뉴가 없습니다. ");
        } else {
            System.out.println("=================================================================");
            System.out.printf("%s\t%s\t%s\t%s\t%s\n", "--도서 제목--", "--저자--", "--설명--", "--가격--", "--카테고리--");
            System.out.println("=================================================================");
            for (Book book : list) {
                System.out.printf("%s\t%s\t%s\t%d\t%s\n",
                        book.getTitle(),
                        book.getAuthor(),
                        book.getDescription(),
                        book.getPrice(),
                        book.getCategory());

            }
        }
    }

    public static void displayDeletedBook(int result) {
        if (result != 0) {
            System.out.println("책 삭제 성공");
        } else {
            System.out.println("책 삭제 실패");
        }
    }

    public static void displayUdateResult(String type, int result) {
        System.out.println(">" + type + " " + (result > 0 ? "🤗성공!!🤗" : "🤗실패!!🤗"));
    }

    public static void displayInsertBook(String type, int result) {
        System.out.println(">" + type + " " + (result > 0 ? "🤗성공!!🤗" : "🤗실패🤗"));
    }


}

