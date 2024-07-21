package com.sh.order.controller;


import com.sh.order.model.entity.Order;
import com.sh.order.model.service.OrderService;
import com.sh.order.view.OrderResultView;

public class OrderController {
    OrderService orderService = new OrderService();

    public void createOrder(Order order) {

        int result = orderService.createOrder(order);
        OrderResultView.displayCreateOrder(result);

    }

    public Order findOrder(int orderId) {
        return orderService.findOrder(orderId);

    }
}
