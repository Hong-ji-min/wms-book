package com.sh.book.model.service;

import com.sh.book.model.dao.BookMapper;
import com.sh.book.model.dto.BookDto;
import com.sh.order.model.entity.Status;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

import static com.sh.common.MyBatisTemplate.getSqlSession;

public class BookService {
    public List<BookDto> findAll() {
        try (SqlSession sqlSession = getSqlSession()) {
            BookMapper bookMapper = sqlSession.getMapper(BookMapper.class);
            return bookMapper.findAll();
        }
    }
    public BookDto findByBookId(int bookId) {
        SqlSession sqlSession = getSqlSession();
        BookMapper bookMapper = sqlSession.getMapper(BookMapper.class);
        BookDto book = bookMapper.findByBookId(bookId);
        sqlSession.close();
        return book;
    }

    public BookDto findByBookTitle(String title) {
        SqlSession sqlSession = getSqlSession();
        BookMapper bookMapper = sqlSession.getMapper(BookMapper.class);
        BookDto book = bookMapper.findByBookTitle(title);
        sqlSession.close();
        return book;
    }

    public BookDto findByAuthor(String author) {
        SqlSession sqlSession = getSqlSession();
        BookMapper bookMapper = sqlSession.getMapper(BookMapper.class);
        BookDto book = bookMapper.findByBookAuthor(author);
        sqlSession.close();
        return book;
    }
    public List<BookDto> findByCategory(String category) {
        SqlSession sqlSession = getSqlSession();
        BookMapper bookMapper = sqlSession.getMapper(BookMapper.class);
        List<BookDto> bookList = bookMapper.findByCategory(category);
        sqlSession.close();
        return bookList;
    }

    public int insertBook(BookDto book) {
        SqlSession sqlSession = getSqlSession();
        BookMapper bookMapper = sqlSession.getMapper(BookMapper.class);
        try {
            int result = bookMapper.insertBook(book);
            sqlSession.commit();
            return result;
        } catch (Exception e) {
            sqlSession.rollback();
            throw new RuntimeException(e);
        } finally {
            sqlSession.close();
        }
    }

    public int updateBook(BookDto book, int bookId) {
        SqlSession sqlSession = getSqlSession();
        BookMapper bookMapper = sqlSession.getMapper(BookMapper.class);
        try {
            int result = bookMapper.updateBook(book, bookId);
            sqlSession.commit();
            return result;
        } catch (Exception e) {
            sqlSession.rollback();
            throw new RuntimeException(e);
        } finally {
            sqlSession.close();
        }
    }
    public int deleteBook(int bookId) {
        SqlSession sqlSession = getSqlSession();
        BookMapper bookMapper = sqlSession.getMapper(BookMapper.class);
        try {
            // fk로 이어진 inventory 삭제
            bookMapper.deleteInventoryByBookId(bookId);
            // 이후 book 삭제
            int result = bookMapper.deleteBook(bookId);
            sqlSession.commit();
            return result;
        } catch (Exception e) {
            sqlSession.rollback();
            throw new RuntimeException(e);
        } finally {
            sqlSession.close();
        }
    }
}
