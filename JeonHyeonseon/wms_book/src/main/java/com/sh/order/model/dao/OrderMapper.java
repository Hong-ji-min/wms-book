package com.sh.order.model.dao;

import com.sh.order.model.dto.OrderDto;

public interface OrderMapper {
    int insertOrder(OrderDto orderDto);
    int insertOrderItem(OrderDto orderDto);
}
