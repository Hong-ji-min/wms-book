package com.sh.order.model.dao;

import com.sh.order.model.entity.Order;
import com.sh.order.model.entity.OrderItem;
import com.sh.order.model.entity.Status;

import java.util.List;

public interface OrderMapper {
    int insertOrder(Order order);
    int insertOrderItem(OrderItem orderItem);
    Order findOrderItem(int orderId);

    List<Order> findByOrderStatus(Status status);

    Order findOrderByOrderId(int orderId);
}
