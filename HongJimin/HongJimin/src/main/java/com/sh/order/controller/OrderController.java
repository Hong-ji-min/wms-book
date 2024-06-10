package com.sh.order.controller;

import com.sh.common.ErrorView;
import com.sh.common.error.ErrorCode;
import com.sh.order.model.entity.Order;
import com.sh.order.model.entity.Status;
import com.sh.order.model.service.OrderService;
import com.sh.order.model.exception.CreateOrderTransactionException;
import com.sh.order.view.OrderResultView;

import java.util.List;

public class OrderController {
    OrderService orderService = new OrderService();

    public void createOrder(Order order) {
        try {
            int result = orderService.createOrder(order);

            Order orderResult = orderService.findOrderItem(order.getOrderId());
            OrderResultView.displayOrderInfo(orderResult);

            OrderResultView.displayResult("도서 주문서 등록 ", result);
        } catch (CreateOrderTransactionException e) {
            e.printStackTrace();
            ErrorView.displayError(e.getErrorCode());
        } catch (Exception e) {
            ErrorView.displayError(ErrorCode.CREATE_ORDER_ERROR);
        }
    }

    public void findByOrderStatus(Status status) {
        try {
            List<Order> orderItemList = orderService.findByOrderStatus(status);
            OrderResultView.displayOrderStatus(orderItemList, status);
        } catch (CreateOrderTransactionException e) {
            e.printStackTrace();
            ErrorView.displayError(e.getErrorCode());
        } catch (Exception e) {
            ErrorView.displayError(ErrorCode.FIND_ORDER_STATUS_ERROR);
        }
    }

    public void findOrderByOrderId(int orderId) {
        try{
            Order order = orderService.findOrderByOrderId(orderId);
            OrderResultView.displayOrderById(order);
        }catch (CreateOrderTransactionException e) {
            e.printStackTrace();
            ErrorView.displayError(e.getErrorCode());
        } catch (Exception e) {
            ErrorView.displayError(ErrorCode.FIND_ORDER_BY_ORDERID_ERROR);
        }
    }
}
