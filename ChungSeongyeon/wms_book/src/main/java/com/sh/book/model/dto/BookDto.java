package com.sh.book.model.dto;

import lombok.*;
import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookDto {
    private int bookId;
    private String title;
    private String author;
    private String description;
    private int price;
    private String category;
    private Timestamp createdAt;


    @Override
    public String toString() {
        return String.format("책정보(도서ID=%d, 도서명='%s', 저자='%s', 설명='%s', 가격=%d, 카테고리='%s', 등록일자=%s)",
                bookId, title, author, description, price, category, createdAt);

    }

}