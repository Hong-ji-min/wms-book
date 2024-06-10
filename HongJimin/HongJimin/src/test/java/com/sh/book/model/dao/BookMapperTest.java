package com.sh.book.model.dao;

import com.sh.book.model.entity.Book;
import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.sh.common.MyBatisTemplate.getSqlSession;
import static org.assertj.core.api.Assertions.assertThat;

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

    @DisplayName("BookId로 책 한 권 조회")
    @Test
    void findBookByBookId() {
        // given 입력받았을때
        int bookId = 1;

        // when 조회햇을때
        Book book = bookMapper.findBookById(bookId);

        // then 그 값을 조회~
        assertThat(book).isNotNull();
        assertThat(book.getBookId()).isEqualTo(bookId);
    }

    @DisplayName("Book 전체 조회")
    @Test
    void findAllBook() {
        // given 입력받았을때

        // when 조회햇을때
        List<Book> bookList = bookMapper.findAllBook();
        System.out.println(bookList);

        // then 그 값을 조회~
        assertThat(bookList)
                .isNotNull()
                .isNotEmpty()
                .allMatch((book) -> book != null)
                .allSatisfy((book) -> {
                    assertThat(book.getBookId()).isNotZero().isPositive();
                    assertThat(book.getTitle()).isNotNull();
                    assertThat(book.getAuthor()).isNotNull();
                    assertThat(book.getCategory()).isNotNull();
                    assertThat(book.getDescription()).isNotNull();
                    assertThat(book.getPrice()).isNotZero().isPositive();
                        });

        for (Book book : bookList) {
            System.out.println(
                    book.getBookId() + "\t\t" +
                            book.getTitle() + "\t\t\t" +
                            book.getAuthor() + "\t\t" +
                            book.getDescription() + "\t\t" +
                            book.getPrice() + "\t\t" +
                            book.getCategory() + "\t\t" +
                            book.getCreatedAt()
            );
        }
    }


    @DisplayName("Book title로 조회")
    @Test
    void findBookByTitle() {
        // given 입력받았을때
        String bookTitle = "데이터베이스 관리";

        // when 조회햇을때
        Book book = bookMapper.findByTitle(bookTitle);

        // then 그 값을 조회~
        assertThat(book).isNotNull();
        System.out.println(
                book.getBookId() + "\t" +
                        book.getTitle() + "\t" +
                        book.getAuthor() + "\t" +
                        book.getDescription() + "\t" +
                        book.getPrice() + "\t" +
                        book.getCategory() + "\t" +
                        book.getCreatedAt()
        );
    }

    @DisplayName("저자명으로 도서 검색")
    @Test
    void findByAuthor() {
        // given 입력받았을때
        String author = "홍길동";

        // when 조회햇을때
        Book book = bookMapper.findByTitle(author);

        // then 그 값을 조회~
        assertThat(book).isNotNull();
        System.out.println(
                book.getBookId() + "\t" +
                        book.getTitle() + "\t" +
                        book.getAuthor() + "\t" +
                        book.getDescription() + "\t" +
                        book.getPrice() + "\t" +
                        book.getCategory() + "\t" +
                        book.getCreatedAt()
        );
    }

    @DisplayName("카테고리로 도서 검색")
    @Test
    void findByCategory() {
        // given 입력받았을때
        String category = "컴퓨터";

        // when 조회햇을때
        List<Book> bookList = bookMapper.findByCategory(category);

        // then 그 값을 조회~
        assertThat(bookList).isNotNull();
        if (bookList.isEmpty()) {
            System.out.println("😅 조회된 도서가 없습니다. 😅");
        } else {
            System.out.println("==================================");
            System.out.println(bookList.get(0).getCategory() + "분야의 도서 목록입니다.");
            System.out.println("=====================================================================================");
            for (Book book : bookList) {
                System.out.println(
                        book.getBookId() + "\t" +
                                book.getTitle() + "\t\t" +
                                book.getAuthor() + "\t" +
                                book.getDescription() + "\t" +
                                book.getPrice() + "\t" +
                                book.getCategory() + "\t" +
                                book.getCreatedAt()
                );
            }
        }
    }

    @DisplayName("가격으로 도서 검색")
    @Test
    void findBookByPrice() {
        // given 입력받았을때
        int price = 30000;

        // when 조회햇을때
        List<Book> bookList = bookMapper.findBookByPrice(price);

        // then 그 값을 조회~
        assertThat(bookList).isNotNull();
        System.out.println("==================================");
        System.out.println("Price : " + bookList.get(0).getPrice() + "원인 도서의 목록입니다.");

        System.out.println("===================================================================================");
        for (Book book : bookList) {
            System.out.println(
                    book.getBookId() + "\t" +
                            book.getTitle() + "\t" +
                            book.getAuthor() + "\t" +
                            book.getDescription() + "\t" +
                            book.getPrice() + "\t" +
                            book.getCategory() + "\t" +
                            book.getCreatedAt()
            );
        }
    }
}
