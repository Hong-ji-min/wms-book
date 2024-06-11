package com.sh.order.model.dao;

import com.sh.order.model.dto.OrderDto;
import com.sh.order.model.dto.OrderItemDto;
import com.sh.order.model.dto.OrderableStatus;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OrderMapper {
    int insertOrder(OrderDto order);

    int insertOrderItem(OrderItemDto orderItem);

    List<OrderDto> selectOrdersByStatus(@Param("status") OrderableStatus status);
    OrderDto selectOrderById(@Param("orderId") int orderId);

    void updateOrderStatus(OrderDto order);
}