package com.sh.order.controller;

import com.sh.common.ErrorView;
import com.sh.common.error.ErrorCode;
import com.sh.order.model.dto.OrderDto;
import com.sh.order.model.dto.Status;
import com.sh.order.model.exception.CreateOrderTransactionException;
import com.sh.order.model.service.OrderService;
import com.sh.order.view.OrderResultView;

import java.util.List;

public class OrderController {
    private OrderService orderService = new OrderService();

    public void createOrder(OrderDto orderDto) {
        try {
            int result = orderService.createOrder(orderDto);
            OrderResultView.displayResult("주문",result);
        } catch (CreateOrderTransactionException e) {
            e.printStackTrace();
            ErrorView.displayError(e.getErrorCode());

        }catch (Exception e) {
            //예외로깅 (디버깅)
//            e.printStackTrace(); // 이후 별도의 파일에 오류로그 기록
            // 사용자 에러메세지 처리
            ErrorView.displayError(ErrorCode.CREATE_ORDER_ERROR);
            // 뷰 이용 이지만 공통적으로 만들어지기 때문에 common 패키지에다가 만들자!
        }


    }

    public List<OrderDto> findOrdersByStatus(Status status) {
        return orderService.findOrdersByStatus(status.getValue());
    }

    public OrderDto findOrderById(int orderId) {
        return orderService.findOrderById(orderId);
    }
}


