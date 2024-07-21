package com.sh.order.model.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemDto {
    private int orderItemId;
    private int orderId;
    private int bookId;
    private int quantity;

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    @Override
    public String toString() {
        return String.format("주문아이템(주문아이템ID=%d, 주문ID=%d, 도서ID=%d, 수량=%d)",
                orderItemId, orderId, bookId, quantity);
    }
}
