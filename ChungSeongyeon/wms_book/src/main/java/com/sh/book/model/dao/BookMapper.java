package com.sh.book.model.dao;

import com.sh.book.model.dto.BookDto;
import org.apache.ibatis.annotations.Param;
import java.util.List;

public interface BookMapper {
    List<BookDto> selectAllBooks();
    BookDto selectBookById(@Param("bookId") int bookId);
    List<BookDto> searchBookByCriteria(@Param("title") String title,
                                       @Param("author") String author,
                                       @Param("category") String category,
                                       @Param("price") Integer price);

    int insertBook(BookDto book);
    int updateBook(BookDto book);
    int deleteBook(@Param("bookId") int bookId);
}
