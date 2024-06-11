package com.sh.book.model.dao;

import com.sh.book.model.dto.BookDto;
import com.sh.order.model.entity.Status;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BookMapper {
    List<BookDto> findAll();

    BookDto findByBookId(int bookId);

    BookDto findByBookTitle(String title);

    BookDto findByBookAuthor(String author);
    List<BookDto> findByCategory(String category);

    int insertBook(BookDto book);

    int updateBook(@Param("book") BookDto book, @Param("bookId") int bookId);

    int deleteBook(int bookId);
    int deleteInventoryByBookId(int bookId);
}
