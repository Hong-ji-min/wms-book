package com.sh.order.controller;

import com.sh.order.model.dto.OrderDto;
import com.sh.order.model.dto.Status;
import com.sh.order.service.OrderService;
import com.sh.order.view.OrderResultView;

import java.util.List;

public class OrderController {
    OrderService orderService = new OrderService();

    public void insertOrder(OrderDto orderDto) {
        try {
            int result = orderService.insertOrder(orderDto);
            OrderResultView.displayResult("주문", result); // 주문 성공, 실패 보여주는 view
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public List<OrderDto> findOrderByStatus(Status status) {
        return orderService.findOrderByStatus(status);
    }


    public OrderDto findOrderById(int orderId) {
        return orderService.findOrderById(orderId);
    }
}
