package com.sh.order_item.model.dto;

import com.sh.book.model.dto.BookDto;
import lombok.*;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@ToString
public class OrderItemDto {
    private int orderItemId;
    private int orderId;
    private int bookId;
    private int quantity;
    private BookDto book;
}
