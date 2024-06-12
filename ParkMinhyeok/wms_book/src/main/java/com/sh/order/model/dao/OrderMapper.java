package com.sh.order.model.dao;

import com.sh.order.model.dto.OrderDto;
import com.sh.order.model.entity.Status;
import com.sh.order_item.model.dto.OrderItemDto;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OrderMapper {
    int insertOrder(OrderDto order);

    int insertOrderItem(OrderItemDto orderItem);

    List<OrderDto> findOrderByStatus(Status status);
    OrderDto findOrderById(int id);
    void updateOrderStatus(@Param("orderId") int orderId, @Param("status") Status status);
}
