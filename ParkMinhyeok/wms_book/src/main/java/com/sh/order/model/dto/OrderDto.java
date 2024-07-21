package com.sh.order.model.dto;

import com.sh.order.model.entity.Status;
import com.sh.order_item.model.dto.OrderItemDto;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@ToString
public class OrderDto {
    private int orderId;
    private String ordererName;
    private String ordererAddress;
    private LocalDateTime orderDate;
    private Status status;
    private List<OrderItemDto> orderItemList;
}
