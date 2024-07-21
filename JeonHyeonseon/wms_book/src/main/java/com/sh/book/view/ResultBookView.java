package com.sh.book.view;

import com.sh.book.model.dto.BookDto;

import java.util.List;

public class ResultBookView {
    public static void displayBookList(List<BookDto> list) {
        if (list.isEmpty()) {
            System.out.println("도서 목록에 해당되지 않습니다.");
        } else {
            System.out.println("------------------------------------------------------------------------------------------------------------------------------");
            System.out.printf("%-5s\t %-22s\t %-23s\t %-20s\t %-15s\t %-20s\t %s\n", "BookID", "Title", "Author", "Description", "Price", "Category", "CreatedAt");
            System.out.println("------------------------------------------------------------------------------------------------------------------------------");
            for (BookDto bookDto : list) {
                System.out.printf("%-5d\t %-20s\t %-15s\t %-15s\t %-15d\t %-15s\t %-10s\n",
                        bookDto.getBookId(),
                        bookDto.getTitle(),
                        bookDto.getAuthor(),
                        bookDto.getDescription(),
                        bookDto.getPrice(),
                        bookDto.getCategory(),
                        bookDto.getCreatedAt());
            }
            System.out.println("------------------------------------------------------------------------------------------------------------------------------");
        }
    }

    public static void displayBook(BookDto bookDto) {
        if (bookDto == null) {
            System.out.println("도서 목록에 해당되지 않습니다.");
        } else {
            System.out.println("------------------------------------------");
            System.out.println("BookID : " + bookDto.getBookId());
            System.out.println("Title : " + bookDto.getTitle());
            System.out.println("Author : " + bookDto.getAuthor());
            System.out.println("Description : " + bookDto.getDescription());
            System.out.println("Price : " + bookDto.getPrice());
            System.out.println("Category : " + bookDto.getCategory());
            System.out.println("CreatedAt : " + bookDto.getCreatedAt());
            System.out.println("------------------------------------------");
        }
    }

    public static void displayResult(String type, int result) {
        System.out.println(">> " + type + (result > 0 ? "완료!!" : "실패ㅠㅠ"));
    }

//    public static void displayBookByPrice(BookDto bookDto) {
//        if (bookDto == null) {
//            System.out.println("입력하신 도서 가격이 해당되지 않습니다.");
//        } else {
//            System.out.println("------------------------------------------------------------------------------------------------------------------------------");
//            System.out.printf("%-5s\t %-22s\t %-23s\t %-20s\t %-15s\t %-20s\t %s\n",  "Title", "Author", "Description", "Price", "Category", "CreatedAt");
//            System.out.println("------------------------------------------------------------------------------------------------------------------------------");
//            for (BookDto bookDto : list) {
//                System.out.printf("%-5d\t %-20s\t %-15s\t %-15s\t %-15d\t %-15s\t %-10s\n",
//                        bookDto.getBookId(),
//                        bookDto.getTitle(),
//                        bookDto.getAuthor(),
//                        bookDto.getDescription(),
//                        bookDto.getPrice(),
//                        bookDto.getCategory(),
//                        bookDto.getCreatedAt());
//            }
//            System.out.println("------------------------------------------------------------------------------------------------------------------------------");
//        }
//    }
}
