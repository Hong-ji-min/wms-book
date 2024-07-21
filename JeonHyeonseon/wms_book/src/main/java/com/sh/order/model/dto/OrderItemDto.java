package com.sh.order.model.dto;

import com.sh.book.model.dto.BookDto;
import lombok.*;
@Data // @ToString, @EqualsAndHashCode, @Getter, @Setter, @RequiredArgsConstructor
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemDto {
    private int orderItemId; // 주문항목 아이디
    @Setter
    private int orderId; // 주문 아이디
    private int bookId; // 도서 아이디
    private int quantity; // 수량

    public OrderItemDto(int orderItemId, int orderId, int bookId, int quantity) {
        this.orderItemId = orderItemId;
        this.orderId = orderId;
        this.bookId = bookId;
        this.quantity = quantity;
    }

    private BookDto bookDto; // 책 제목을 입력하기 위해서 필요함
}
