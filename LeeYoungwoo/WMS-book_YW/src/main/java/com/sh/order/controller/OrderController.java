package com.sh.order.controller;

import com.sh.book.model.dto.BookDto;
import com.sh.book.view.BookResultView;
import com.sh.order.model.dto.OrderDto;
import com.sh.order.model.service.OrderService;
import com.sh.order.view.OrderResultView;

import java.util.List;

public class OrderController {
    private OrderService orderService = new OrderService();

    public void findAll() {
        List<BookDto> list = orderService.findAll();
        BookResultView.displayBookList(list);
    }

    public void createOrder(OrderDto order) {
        try {
            int result = orderService.createOrder(order);
            OrderResultView.displayResult(result);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
