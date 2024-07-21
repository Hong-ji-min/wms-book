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

    // 도서 등록
    public void insertBook(BookDto bookDto) {
        int result = bookService.insertBook(bookDto);
        BookResultView.displayResult("도서 등록",result);
    }

    public void deleteBook(int bookId) {
        int result = bookService.deleteBook(bookId);
        BookResultView.displayDeleteResult("도서 삭제",result);
    }
    // 수정
    public void updateBook(BookDto bookDto) {
        int result = bookService.updateBook(bookDto);
        BookResultView.displayResult("도서 수정",result);

    }

    public List<BookDto> findByBookIdList(int bookId) {
        List<BookDto> list = bookService.findByBookIdList(bookId);
        BookResultView.displayBookList(list,"도서코드 : "+ bookId + "의 목록입니다.");
        return list;
    }
    // 리스트로 반환하자.
}
