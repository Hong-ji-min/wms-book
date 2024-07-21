package com.sh.book.model.entity;

import lombok.*;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

@ToString
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Book {
    private int bookId;
    private String title;
    private String author;
    private String description;
    private int price;
    private String category;
    private LocalDateTime createdAt;


}
