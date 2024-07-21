package com.sh.order.controller;

import com.sh.order.model.dto.OrderDto;
import com.sh.order.model.dto.OrderableStatus;
import com.sh.order.model.service.OrderService;

import java.util.List;

public class OrderController {
    private OrderService orderService = new OrderService();

    public void registerOrder(OrderDto order) {
        orderService.registerOrder(order);
    }
    public List<OrderDto> getOrdersByStatus(OrderableStatus status) {
        return orderService.getOrdersByStatus(status);
    }

    public OrderDto getOrderById(int orderId) {
        return orderService.getOrderById(orderId);
    }
}
