package com.sh.book.controller;

import com.sh.book.view.BookResultView;
import com.sh.book.model.dto.BookDto;
import com.sh.book.model.service.BookService;

import java.util.List;

public class BookController {

    private BookService bookService = new BookService();

    public void findAll() {
        List<BookDto> list = bookService.findAll();
        BookResultView.displayBookList(list,"😊전체도서 목록입니다.😊");
    }

    public void findByBookId(int bookId){
        BookDto bookDto = bookService.findByBookId(bookId);
        BookResultView.displayBook(bookDto);
    }

    // 상세조회할때 도서명으로 검색
    public void findByTitle(String title){
        List<BookDto> list = bookService.findByTitle(title);
        BookResultView.displayBookList(list,"🐥도서명 : "+title + "의 목록입니다.🐥");
    }
    public void findByAuthor(String author){
        List<BookDto> list = bookService.findByAuthor(author);
        BookResultView.displayBookList(list,"🐥저자 : "+author + "의 목록입니다.🐥");
    }

    public void findByCategory(String category){
        List<BookDto> list = bookService.findByCategory(category);
        BookResultView.displayBookList(list,"🐥카테고리 : "+category + "의 목록입니다.🐥");
    }
    public void findByPrice(int price){
        List<BookDto> list = bookService.findByPrice(price);
        BookResultView.displayBookList(list,"🐥가격 : "+price + "의 목록입니다.🐥");
    }
}
