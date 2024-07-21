package com.sh.book.model.dao;

import com.sh.book.model.entity.Book;

import java.util.List;

public interface BookMapper {
    List<Book> findAll();

    Book findByBookId(int bookId);

    List<Book> findByAuthor(String author);

    List<Book> findByCategory(String category);

    int deleteBookById(int bookId);

    int updateBookInfo(Book book);

    int insertBook(Book book);
}
