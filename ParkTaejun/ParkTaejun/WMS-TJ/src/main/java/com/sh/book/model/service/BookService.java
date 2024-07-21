package com.sh.book.model.service;

import com.sh.book.model.dao.BookMapper;
import com.sh.book.model.entity.Book;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

import static com.sh.common.MyBatisTemplate.getSqlSession;

public class BookService {

    public List<Book> findAll() {
        SqlSession sqlSession = getSqlSession();

        BookMapper bookMapper = sqlSession.getMapper(BookMapper.class);
        List<Book> list = bookMapper.findAll();
        sqlSession.close();
        return list;

    }

    public Book findByBookId(int bookId) {
        SqlSession sqlSession = getSqlSession();
        BookMapper bookMapper = sqlSession.getMapper(BookMapper.class);
        return bookMapper.findByBookId(bookId);


    }

    public List<Book> findByAuthor(String author) {
        SqlSession sqlSession = getSqlSession();
        BookMapper bookMapper = sqlSession.getMapper(BookMapper.class);
        List<Book> list = bookMapper.findByAuthor(author);
        return list;
    }

    public List<Book> findByCategory(String category) {
        SqlSession sqlSession = getSqlSession();
        BookMapper bookMapper = sqlSession.getMapper(BookMapper.class);
        List<Book> list = bookMapper.findByCategory(category);
        return list;
    }

    public int deleteBookById(int bookId) {
        SqlSession sqlSession = getSqlSession();
        BookMapper bookMapper = sqlSession.getMapper(BookMapper.class);
        try {
            int result = bookMapper.deleteBookById(bookId);
            sqlSession.commit();
            return result;
        } catch (Exception e) {
            sqlSession.rollback();
            throw new RuntimeException(e);
        } finally {
            sqlSession.close();
        }

    }

    public int updateBookInfo(Book book) {
        SqlSession sqlSession = getSqlSession();
        BookMapper bookMapper = sqlSession.getMapper(BookMapper.class);
        try {
            int result = bookMapper.updateBookInfo(book);
            sqlSession.commit();
            return result;
        } catch (Exception e) {
            sqlSession.rollback();
            throw new RuntimeException(e);
        } finally {
            sqlSession.close();

        }


    }
    public int insertBook(Book book) {
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

}