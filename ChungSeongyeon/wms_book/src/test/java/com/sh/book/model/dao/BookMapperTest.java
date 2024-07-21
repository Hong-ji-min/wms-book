package com.sh.book.model.dao;

import com.sh.book.model.dao.BookMapper;
import com.sh.book.model.dto.BookDto;
import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.Test;

import java.util.List;
import static com.sh.common.MyBatisTemplate.getSqlSession;
import static org.junit.jupiter.api.Assertions.*;

public class BookMapperTest {
    @Test
    public void testSelectAllBooks() {
        try (SqlSession sqlSession = getSqlSession()) {
            BookMapper bookMapper = sqlSession.getMapper(BookMapper.class);
            List<BookDto> books = bookMapper.selectAllBooks();
            assertNotNull(books);
            assertFalse(books.isEmpty());

            System.out.println("--[testSelectAllBooks]--");
            for (BookDto book : books) {
                System.out.println(book);
            }
            System.out.println();
        }
    }

    @Test
    public void testSelectBookById() {
        try (SqlSession sqlSession = getSqlSession()) {
            BookMapper bookMapper = sqlSession.getMapper(BookMapper.class);
            BookDto book = bookMapper.selectBookById(1);
            assertNotNull(book);
            assertEquals(1, book.getBookId());

            System.out.println("--[testSelectBookById]--");
            System.out.println(book);
            System.out.println();
        }
    }

    @Test
    public void testSearchBookByCriteria() {
        try (SqlSession sqlSession = getSqlSession()) {
            BookMapper bookMapper = sqlSession.getMapper(BookMapper.class);
            List<BookDto> books = bookMapper.searchBookByCriteria("데이터베이스 관리", "홍길동", "컴퓨터", null);
            assertNotNull(books);
            assertFalse(books.isEmpty());

            System.out.println("--[testSearchBooks]--");
            for (BookDto book : books) {
                System.out.println(book);
            }
            System.out.println();
        }
    }

    @Test
    public void testInsertBook() {
        try (SqlSession sqlSession = getSqlSession()) {
            BookMapper bookMapper = sqlSession.getMapper(BookMapper.class);
            BookDto book = new BookDto();
            book.setTitle("테스트 도서");
            book.setAuthor("테스트 저자");
            book.setDescription("테스트 설명");
            book.setPrice((int) 10000.00);
            book.setCategory("테스트 카테고리");
            int result = bookMapper.insertBook(book);
            sqlSession.commit();
            assertEquals(1, result);
            System.out.println("추가된 도서의 ID: " + book.getBookId());
        }
    }

    @Test
    public void testUpdateBook() {
        try (SqlSession sqlSession = getSqlSession()) {
            BookMapper bookMapper = sqlSession.getMapper(BookMapper.class);
            BookDto book = new BookDto();
            book.setBookId(33); // 수정할 도서의 ID:33 -> test 돌린거 33번임
            book.setTitle("수정된 도서 제목");
            book.setAuthor("수정된 저자");
            book.setDescription("수정된 도서 설명");
            book.setPrice((int) 20000.00);
            book.setCategory("수정된 카테고리");
            int result = bookMapper.updateBook(book);
            sqlSession.commit();
            assertEquals(1, result);
            System.out.println("수정된 도서의 ID: " + book.getBookId());
        }
    }

    @Test
    public void testDeleteBook() {
        try (SqlSession sqlSession = getSqlSession()) {
            BookMapper bookMapper = sqlSession.getMapper(BookMapper.class);
            int bookId = 33; // 삭제할 도서의 ID:33
            int result = bookMapper.deleteBook(bookId);
            sqlSession.commit();
            assertEquals(1, result);
            System.out.println("삭제된 도서의 ID: " + bookId);
        }
    }

}
