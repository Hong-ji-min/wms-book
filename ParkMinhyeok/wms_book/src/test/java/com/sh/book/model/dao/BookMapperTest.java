package com.sh.book.model.dao;

import com.sh.book.model.dto.BookDto;
import com.sh.order.model.entity.Status;
import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static com.sh.common.MyBatisTemplate.getSqlSession;
import static org.junit.jupiter.api.Assertions.*;

class BookMapperTest {
    SqlSession sqlSession;
    BookMapper bookMapper;
    @BeforeEach
    void setUp() {
        this.sqlSession = getSqlSession();
        this.bookMapper = this.sqlSession.getMapper(BookMapper.class);
    }

    @AfterEach
    void tearDown() {
        this.sqlSession.close();
    }
    @DisplayName("Test find All")
    @Test
    void findAll() {
        // given

        // when
        List<BookDto> bookList = bookMapper.findAll();
        // then
        assertNotNull(bookList);
        assertFalse(bookList.isEmpty());
//        for (BookDto book : bookList) {
//            System.out.println(book);
//        }
    }
    @DisplayName("Test find by bookId")
    @Test
    void findByBookId() {
        // given
        int bookId = 1;
        // when
        BookDto book = bookMapper.findByBookId(bookId);
        // then
        assertNotNull(book);
        assertEquals(bookId, book.getBookId());
//        System.out.println(book);
    }
    @DisplayName("Test find by book title")
    @Test
    void findByBookTitle() {
        // given
        String title = "딥러닝 실전";
        // when
        BookDto book = bookMapper.findByBookTitle(title);
        // then
        assertNotNull(book);
        assertEquals(title, book.getTitle());
//        System.out.println(book);
    }
    @DisplayName("Test find by bookAuthor")
    @Test
    void findByBookAuthor() {
        // given
        String author = "홍길동";
        // when
        BookDto book = bookMapper.findByBookAuthor(author);
        // then
        assertNotNull(book);
        assertEquals(author, book.getAuthor());
//        System.out.println(book);
    }
    @DisplayName("Test insert Book")
    @Test
    void insertBook() {
        // given
        BookDto book = new BookDto();
        book.setTitle("테스트");
        book.setAuthor("테스트 저자");
        book.setDescription("테스트 테스트");
        book.setPrice(12000);
        book.setCategory("역사");
        book.setCreatedAt(LocalDateTime.now());
        // when
        int result = bookMapper.insertBook(book);
        System.out.println(result);
        // then
        assertEquals(result, 1);
    }
    @DisplayName("Test update book")
    @Test
    void updateBook() {
        int bookId = 1;
        // given
        BookDto book = new BookDto();
        book.setBookId(bookId);
        book.setTitle("테스트");
        book.setAuthor("테스트 저자");
        book.setDescription("테스트 테스트");
        book.setPrice(12000);
        book.setCategory("역사");
        // when
        int result = bookMapper.updateBook(book, bookId);
        // then
        assertEquals(result, 1);
    }
    @DisplayName("Test delete book")
    @Test
    void deleteBook() {
        // given
        int bookId = 1;
        // when
        int result = bookMapper.deleteBook(bookId);
        // then
        assertEquals(result, 1);
    }
}