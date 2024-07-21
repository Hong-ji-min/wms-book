package com.sh.order.model.entity;



import com.sh.book.model.entity.Book;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderItem {
    private int orderItemId;
    private int orderId;
    private int bookId;
    private int quantity;

    private Order order;

    // 1 : 1 관계를 표현하기 위함임 (association)
    private Book book;
}

