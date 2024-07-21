package com.sh.book.model.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@NoArgsConstructor // 기본생성자
@AllArgsConstructor // 모든 매개인자 생성자
@Getter
@Setter
//@ToString

public class BookDto {
    private int bookId;
    private String title;
    private String author;
    private String description;
    private int price;
    private String category;
    private LocalDateTime createdAt;

    @Override
    public String toString() {
        return "BookDto{" +
                "bookId=" + bookId +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", createdAt=" + (createdAt != null ? createdAt.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME) : "null") +
                '}';
    }
}



