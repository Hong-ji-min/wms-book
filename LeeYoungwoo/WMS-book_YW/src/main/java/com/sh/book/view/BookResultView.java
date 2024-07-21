package com.sh.book.view;

import com.sh.book.model.dto.BookDto;

import java.util.List;

public class BookResultView {
    public static void displayBookList(List<BookDto> list) {
        if (list.isEmpty()) {
            System.out.println("😅😅😅 조회된 목록이 없습니다. 😅😅😅");
        } else {
            System.out.println("-------------------------------------------------------------------------------------------");
            System.out.printf("%s\t%s\t%s\t%s\t%s\t%s\t%s\n", "도서 ID", "도서 제목", "저자", "설명", "가격", "카테고리", "생성 일시");
            System.out.println("-------------------------------------------------------------------------------------------");
            for(BookDto bookDto : list) {
                System.out.printf("%d\t%s\t%s\t%s\t%d\t%s\t%s\n",
                        bookDto.getBookId(),
                        bookDto.getTitle(),
                        bookDto.getAuthor(),
                        bookDto.getDescription(),
                        bookDto.getPrice(),
                        bookDto.getCategory(),
                        bookDto.getCreatedAt()
                        );
            }
            System.out.println("-------------------------------------------------------------------------------------------");

        }
    }

    public static void displayBookIdList(BookDto bookDto) {
        if (bookDto == null) {
            System.out.println("😅😅😅 조회된 목록이 없습니다. 😅😅😅");
        } else {
            System.out.println("-------------------------------------------------------------------------------------------");
            System.out.printf("%s\t%s\t%s\t%s\t%s\t%s\t%s\n", "도서 ID", "도서 제목", "저자", "설명", "가격", "카테고리", "생성 일시");
            System.out.println("-------------------------------------------------------------------------------------------");
                System.out.printf("%d\t%s\t%s\t%s\t%d\t%s\t%s\n",
                        bookDto.getBookId(),
                        bookDto.getTitle(),
                        bookDto.getAuthor(),
                        bookDto.getDescription(),
                        bookDto.getPrice(),
                        bookDto.getCategory(),
                        bookDto.getCreatedAt()
                );
            System.out.println("-------------------------------------------------------------------------------------------");

        }
    }

    public static void displaySearchBook(List<BookDto> bookList) {
//        System.out.println(bookList);

        if (bookList.isEmpty()) {
            System.out.println("😅😅😅 조회된 목록이 없습니다. 😅😅😅");
        } else {
            System.out.println("-------------------------------------------------------------------------------------------");
            System.out.printf("%s\t%s\t%s\t%s\t%s\t%s\t%s\n", "도서 ID", "도서 제목", "저자", "설명", "가격", "카테고리", "생성 일시");
            System.out.println("-------------------------------------------------------------------------------------------");
            for(BookDto bookDto : bookList) {
                System.out.printf("%d\t%s\t%s\t%s\t%d\t%s\t%s\n",
                        bookDto.getBookId(),
                        bookDto.getTitle(),
                        bookDto.getAuthor(),
                        bookDto.getDescription(),
                        bookDto.getPrice(),
                        bookDto.getCategory(),
                        bookDto.getCreatedAt()
                );
            }
            System.out.println("-------------------------------------------------------------------------------------------");

        }
    }

    public static void displayInsertBook(int result) {
        System.out.println("> 📢📢📢 " + "도서 등록 " + (result > 0 ? "성공!" : "실패!") + " 📢📢📢");
    }

    public static void displayUpdateBook(int result) {
        System.out.println("> 📢📢📢 " + "도서 수정 " + (result > 0 ? "성공!" : "실패!") + " 📢📢📢");
    }

    public static void displayDeleteBook(int result) {
        System.out.println("> 📢📢📢 " + "도서 삭제 " + (result > 0 ? "성공!" : "실패!") + " 📢📢📢");
    }
}
