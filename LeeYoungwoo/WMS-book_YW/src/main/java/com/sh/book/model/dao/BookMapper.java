package com.sh.book.model.dao;

import com.sh.book.model.dto.BookDto;
import com.sh.common.SearchCriteria;

import java.util.List;

public interface BookMapper {
    List<BookDto> findAll();
    BookDto findByBookId(int bookId);
    List<BookDto> findByTitle(String title);
    List<BookDto> findByAuthor(String author);
    List<BookDto> findByCategory(String category);
    List<BookDto> findByPrice(int price);

    List<BookDto> searchMenu(SearchCriteria searchCriteria);

    int insertBook(BookDto bookDto);

    int updateBook(BookDto bookDto);

    int deleteBook(int bookId);
}
