package com.sh.book.model.dao;

import com.sh.book.model.dto.BookDto;
import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.sh.common.MyBatisTemplate.getSqlSession;
import static org.assertj.core.api.Assertions.assertThat;
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

    @DisplayName("도서 목록 전체 조회")
    @Test
    void findAllBooks() {
        List<BookDto> books = bookMapper.findAllBooks();
        // then
        assertThat(books)
                .isNotNull()
                .isNotEmpty()
                .allMatch((book) -> book != null)
                .allSatisfy((book) -> {
                    assertThat(book.getBookId()).isNotZero().isPositive();
                    assertThat(book.getTitle()).isNotNull();
                    assertThat(book.getAuthor()).isNotNull();
                    assertThat(book.getDescription()).isNotNull();
                    assertThat(book.getPrice()).isNotZero().isPositive();
                    assertThat(book.getCategory()).isNotNull();
                });

    }

    @DisplayName("도서 아이디로 조회")
    @Test
    void findByBookId() {
        // given
        int bookId = 1;
        // when
        BookDto book = bookMapper.findByBookId(bookId);
        // then
        assertThat(book).isNotNull();
        assertThat(book.getBookId()).isEqualTo(bookId);
        assertThat(book.getTitle()).isNotNull();
        assertThat(book.getAuthor()).isNotNull();
        assertThat(book.getDescription()).isNotNull();
        assertThat(book.getPrice()).isNotZero().isPositive();
        assertThat(book.getCategory()).isNotNull();
    }

    @DisplayName("도서명으로 검색")
    @Test
    void findByTitle() {
        // given
        String title = "데이터베이스 관리";
        // when
        List<BookDto> bookDto = bookMapper.findByTitle(title);
        // then
        assertThat(bookDto).isNotNull();
        assertThat(bookDto).isNotEmpty();
        assertThat(bookDto.get(0).getTitle()).isEqualTo(title);
    }

    @DisplayName("저자로 검색")
    @Test
    void findByAuthor() {
        // given
        String author = "김철수";
        // when
        List<BookDto> bookDto = bookMapper.findByAuthor(author);
        // then
        assertThat(bookDto).isNotNull();
        assertThat(bookDto).isNotEmpty();
        assertThat(bookDto.get(0).getAuthor()).isEqualTo(author);
    }

    @DisplayName("카테고리로 검색")
    @Test
    void findByCategory() {
        // given
        String category = "인공지능";
        // when
        List<BookDto> bookDto = bookMapper.findByCategory(category);
        // then
        assertThat(bookDto).isNotNull();
        assertThat(bookDto).isNotEmpty();
        assertThat(bookDto.get(0).getCategory()).isEqualTo(category);
    }

    @DisplayName("가격으로 검색")
    @Test
    void findByPrice() {
        // given
//        int price =
    }

    @DisplayName("도서 등록")
    @Test
    void insertBook() {
        // given
        String title = "혼자 공부하는 자바";
        String author = "미나리";
        String description = "스스로 공부할 수 있는 기본적인 자바 책";
        int price = 25000;
        String category = "컴퓨터";
        BookDto bookDto = new BookDto(0, title, author, description, price, category, null);
        // when
        int result = bookMapper.insertBook(bookDto);
        //then
        assertThat(result).isEqualTo(1);
        int bookId = bookDto.getBookId();
        assertThat(bookDto.getBookId()).isNotZero();
        System.out.println(bookId);
        // 등록된 행을 조회해서 컬럼값 비교
        BookDto bookDto1 = bookMapper.findByBookId(bookId);
        assertThat(bookDto1.getBookId()).isEqualTo(bookId);
        assertThat(bookDto1.getTitle()).isEqualTo(title);
        assertThat(bookDto1.getAuthor()).isEqualTo(author);
        assertThat(bookDto1.getDescription()).isEqualTo(description);
        assertThat(bookDto1.getPrice()).isEqualTo(price);
    }

    @DisplayName("도서 아이디/등록일을 제외하고 도서 수정")
    @Test
    void updateBook() {
        // given 임의의 데이터 생성
        String title = "혼자 공부하는 자바";
        String author = "미나리";
        String description = "스스로 공부할 수 있는 기본적인 자바 책";
        int price = 25000;
        String category = "컴퓨터";
        BookDto bookDto = new BookDto(0, title, author, description, price, category, null);
        // 새 데이터 입력
        bookMapper.insertBook(bookDto);
        int bookId = bookDto.getBookId();
        //수정할 데이터
        String newTitle = "삼각함수";
        String newAuthor = "개나리";
        String newDescription = "삼각함수에 대한 기본개념";
        int newPrice = 15500;
        String newCategory = "수학";
        bookDto.setTitle(newTitle);
        bookDto.setAuthor(newAuthor);
        bookDto.setDescription(newDescription);
        bookDto.setPrice(newPrice);
        bookDto.setCategory(newCategory);
        // when
        int result = bookMapper.updateBook(bookDto);
        // then
        assertThat(result).isEqualTo(1);
        // 등록된 행을 조회해서 컬럼값 비교
        BookDto bookDto1 = bookMapper.findByBookId(bookId);
        assertThat(bookDto1.getTitle()).isEqualTo(newTitle);
        assertThat(bookDto1.getAuthor()).isEqualTo(newAuthor);
        assertThat(bookDto1.getDescription()).isEqualTo(newDescription);
        assertThat(bookDto1.getPrice()).isEqualTo(newPrice);
        assertThat(bookDto1.getCategory()).isEqualTo(newCategory);
    }

    @DisplayName("도서 아이디를 입력하여 도서 삭제")
    @Test
    void deleteBook() {
        // given 임의의 데이터생성
        String title = "혼자 공부하는 자바";
        String author = "미나리";
        String description = "스스로 공부할 수 있는 기본적인 자바 책";
        int price = 25000;
        String category = "컴퓨터";
        BookDto bookDto = new BookDto(0, title, author, description, price, category, null);
        // 새 데이터 입력
        bookMapper.insertBook(bookDto);
        int bookId = bookDto.getBookId();
        // when
        int result = bookMapper.deleteBook(bookId);
        // then
        assertThat(result).isEqualTo(1);
        assertThat(bookMapper.findByBookId(bookId)).isNull();
    }
}