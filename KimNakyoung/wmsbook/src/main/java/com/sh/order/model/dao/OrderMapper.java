package com.sh.order.model.dao;

import com.sh.order.model.dto.OrderDto;
import com.sh.order.model.dto.OrderItemDto;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface OrderMapper {
    int insertOrder(OrderDto orderDto);

    int insertOrderItem(OrderItemDto orderItemDto);

    // 상태별 주문 목록 조회
    List<OrderDto> findOrdersByStatus(String status);

    // 주문번호로 주문 정보 조회
    OrderDto findOrderById(int orderId);

    // 주문 상태 업데이트
    int updateOrderStatus(@Param("orderId") int orderId, @Param("status") String status);
}
