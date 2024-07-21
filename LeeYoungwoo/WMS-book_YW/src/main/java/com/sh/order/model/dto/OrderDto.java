package com.sh.order.model.dto;

import com.sh.order.model.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto {
    private int orderId;
    private String ordererName;
    private String ordererAddress;
    private LocalDateTime orderDate;
    private OrderStatus status;

    private List<OrderItemDto> orderList;
}
