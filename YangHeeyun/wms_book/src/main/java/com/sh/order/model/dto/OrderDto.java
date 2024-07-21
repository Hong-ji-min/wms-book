package com.sh.order.model.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor // 기본생성자
@AllArgsConstructor // 모든 매개인자 생성자
@Getter
@Setter
@ToString

public class OrderDto {
    private int orderId;
    private String ordererName;
    private String ordererAddress;
    private LocalDateTime orderDate;
    private Status status;
    private List<OrderItemDto> orderItemList; // 1:N 관계를 객체지향에 표현하는 방식
}
