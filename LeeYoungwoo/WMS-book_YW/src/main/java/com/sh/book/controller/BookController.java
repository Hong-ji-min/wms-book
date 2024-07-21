package com.sh.book.controller;

import com.sh.book.model.dto.BookDto;
import com.sh.book.model.service.BookService;
import com.sh.book.view.BookResultView;
import com.sh.common.SearchCriteria;

import java.util.List;

public class BookController {
    private BookService bookService = new BookService();
    public void findAll() {
        List<BookDto> list = bookService.findAll();
        BookResultView.displayBookList(list);
    }

    public void findByBookId(int bookId) {
        BookDto bookDto = bookService.findByBookId(bookId);
        BookResultView.displayBookIdList(bookDto);
    }

    public void searchMenu(SearchCriteria searchCriteria) {
        List<BookDto> bookList = bookService.searchMenu(searchCriteria);
        BookResultView.displaySearchBook(bookList);
    }

    public void insertBook(BookDto bookDto) {
        int result = bookService.insertBook(bookDto);
        BookResultView.displayInsertBook(result);

    }

    public void updateBook(BookDto bookDto) {
        int result = bookService.updateBook(bookDto);
        BookResultView.displayUpdateBook(result);
    }

    public void deleteBook(int bookId) {
        int result = bookService.deleteBook(bookId);
        BookResultView.displayDeleteBook(result);
    }
}
