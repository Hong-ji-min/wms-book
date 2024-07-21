package com.sh.book.model.dao;

import com.sh.book.model.dto.BookDto;

import java.util.List;

public interface BookMapper {
    List<BookDto> findAllBooks();

    BookDto findByBookId(int bookId);

    List<BookDto> findByTitle(String title);

    List<BookDto> findByAuthor(String author);

    List<BookDto> findByCategory(String category);

    List<BookDto> findByPrice(int price);

    int insertBook(BookDto bookDto);

    int updateBook(BookDto bookDto);

    int deleteBook(int bookId);
}
