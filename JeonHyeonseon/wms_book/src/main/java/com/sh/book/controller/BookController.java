package com.sh.book.controller;

import com.sh.book.model.dto.BookDto;
import com.sh.book.model.service.BookService;
import com.sh.book.view.ResultBookView;

import java.util.List;


public class BookController {
    private BookService bookService = new BookService();

    public void findAllBooks() {
        try {
            List<BookDto> list = bookService.findAllBooks();
            ResultBookView.displayBookList(list);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void findByBookId(int bookId) {
        try {
            BookDto bookDto = bookService.findByBookId(bookId);
            ResultBookView.displayBook(bookDto);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void findByTitle(String title) {
        try {
            List<BookDto> list = BookService.findByTitle(title);
            ResultBookView.displayBookList(list);
        }catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void findByAuthor(String author) {
        try{
            List<BookDto> bookDto = BookService.findByAuthor(author);
            ResultBookView.displayBookList(bookDto);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void findByCategory(String category) {
        try {
            List<BookDto> bookDto = BookService.findByCategory(category);
            ResultBookView.displayBookList(bookDto);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void findByPrice(int price) {
        try {
            List<BookDto> bookDto = BookService.findByPrice(price);
            ResultBookView.displayBookList(bookDto);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void insertBook(BookDto bookDto) {
        int result = bookService.insertBook(bookDto);
        ResultBookView.displayResult("도서 등록", result);
    }

    public void updateBook(BookDto bookDto) {
        int result = bookService.updateBook(bookDto);
        ResultBookView.displayResult("도서 수정", result);
    }

    public void deleteBook(int bookId) {
        int result = bookService.deleteBook(bookId);
        ResultBookView.displayResult("도서 삭제", result);
    }
}
