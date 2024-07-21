package com.sh.book.controller;

import com.sh.book.model.entity.Book;
import com.sh.book.model.service.BookService;
import com.sh.book.view.BookResultView;

import java.util.List;

public class BookController {


    BookService bookService = new BookService();

    public void findAll() {
        List<Book> list = bookService.findAll();
        BookResultView.displayBookList(list);

    }

    public Book findByBookId(int bookId) {

        return bookService.findByBookId(bookId);

    }


    public void findByAuthor(String author) {
        List<Book> list = bookService.findByAuthor(author);
        BookResultView.displayBookByAuthor(list);

    }

    public List<Book> findByCategry(String category) {
        List<Book> list = bookService.findByCategory(category);
        BookResultView.displayBookByCategory(list);
        return list;
    }

    public void deleteBookById(int bookId) {
        int result = bookService.deleteBookById(bookId);
        BookResultView.displayDeletedBook(result);

    }

    public void updateBookInfo(Book book) {
        int result = bookService.updateBookInfo(book);
        BookResultView.displayUdateResult(" 책 정보 수정 : ", result);

    }

    public void inserBook(Book book) {
        int result = bookService.insertBook(book);
        BookResultView.displayInsertBook("책 등록 : ", result);

    }
}

