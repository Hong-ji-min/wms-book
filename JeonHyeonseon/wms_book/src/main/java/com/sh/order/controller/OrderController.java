package com.sh.order.controller;

import com.sh.order.model.dto.OrderDto;
import com.sh.order.model.service.OrderService;
import com.sh.order.view.ResultOrderView;

public class OrderController {
    private OrderService orderService = new OrderService();

    public void createOrder(OrderDto orderDto) {
        int result = orderService.createOrder(orderDto);
        ResultOrderView.displayOrderBook(result);
    }
}
