package com.sh.book.model.dao;

import com.sh.book.model.dto.BookDto;

import java.util.List;

public interface BookMapper {
    List<BookDto> findAll(); // 전체조회
    BookDto findByBookId(int bookId); // 도서코드로 한개의 도서정보 조회
    List<BookDto> findByTitle(String title); // 상세검색중 도서명으로 검색
    List<BookDto> findByAuthor(String author); // 상세검색중 저자로 검색
    List<BookDto> findByCategory(String category); // 상세검색중 카테고리로 검색
    List<BookDto> findByPrice(int price); // 상세검색중 카테고리로 검색

    // 쿼리에 전달하는 객체는 무조건 1개여야한다. 묶어서 줘라! Dto 형식 아니면 키 벨류 값이라도 !
    // 지금은 Dto방식 씀.
    int insertBook(BookDto bookDto);

    int deleteBook(int bookId); // 테스트 삽입한거 삭제용 메서드
    int updateBook(BookDto bookDto);

    List<BookDto> findByBookIdList(int bookId);
}
