package com.sh.order.model.dto;

import lombok.*;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto {
    private int orderId;
    private String ordererName;
    private String ordererAddress;
    private LocalDate orderDate;
    private OrderableStatus status;

    private List<OrderItemDto> orderItems;

    public List<OrderItemDto> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItemDto> orderItems) {
        this.orderItems = orderItems;
    }

    @Override
    public String toString() {
        return String.format("주문정보(주문ID=%d, 주문자='%s', 주소='%s', 주문일자=%s, 상태='%s')",
                orderId, ordererName, ordererAddress, orderDate, status);
    }
}
