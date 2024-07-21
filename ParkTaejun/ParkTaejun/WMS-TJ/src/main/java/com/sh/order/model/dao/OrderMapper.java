package com.sh.order.model.dao;

import com.sh.order.model.entity.Order;
import com.sh.order.model.entity.OrderItem;

public interface OrderMapper {
    int insertOrder(Order order);

    int insertOrderItem(OrderItem orderItem);

    Order findOrder(int orderId);
}
