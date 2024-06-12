package com.sh.order.controller;

import com.sh.order.model.dto.OrderDto;
import com.sh.order.model.entity.Status;
import com.sh.order.model.service.OrderService;
import com.sh.order.view.OrderResultView;

import java.util.List;

public class OrderController {
    private OrderService orderService = new OrderService();
    public void createOrder(OrderDto order) {
        try {
            int result = orderService.createOrder(order);
            System.out.println(result == 1 ? "주문 성공" : "주문 실패");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public List<OrderDto> findOrderByStatus(Status status) {
        return orderService.findOrderByStatus(status);
    }

    public OrderDto findOrderById(int id) {
        return orderService.findOrderById(id);
    }
}
