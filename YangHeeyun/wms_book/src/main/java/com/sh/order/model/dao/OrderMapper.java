package com.sh.order.model.dao;

import com.sh.order.model.dto.OrderDto;
import com.sh.order.model.dto.OrderItemDto;
import com.sh.order.model.dto.Status;

import java.util.List;

public interface OrderMapper {
    // 한 건의 주문등록
    int insertOrder(OrderDto orderDto);

    // 여러 건의 주문등록
    int insertOrderItem(OrderItemDto orderItemDto);

    // 주문상태별 주문자 정보 조회
    List<OrderDto> findOrderByStatus(Status status);

    // 주문번호로 주문 정보 조회
    OrderDto findOrderById(int orderId);


}

