package com.sh.order.model.service;

import com.sh.order.model.dao.OrderMapper;
import com.sh.order.model.dto.OrderDto;
import com.sh.order.model.entity.Status;
import com.sh.order_item.model.dto.OrderItemDto;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

import static com.sh.common.MyBatisTemplate.getSqlSession;

public class OrderService {
    public int createOrder(OrderDto order) {
        SqlSession sqlSession = getSqlSession();
        OrderMapper orderMapper = sqlSession.getMapper(OrderMapper.class);
        int result = 0;
        try {
            // 1건의 주문 등록
            result = orderMapper.insertOrder(order);
            System.out.println(order.getOrderId());
            // n건의 주문 등록
            for (OrderItemDto orderItem : order.getOrderItemList()) {
                orderItem.setOrderId(order.getOrderId());
                result = orderMapper.insertOrderItem(orderItem);
            }
            sqlSession.commit();
            return result;
        } catch (Exception e) {
            sqlSession.rollback();
        } finally {
            sqlSession.close();
        }
        return result;
    }
    public List<OrderDto> findOrderByStatus(Status status) {
        SqlSession sqlSession = getSqlSession();
        OrderMapper orderMapper = sqlSession.getMapper(OrderMapper.class);
        try {
            return orderMapper.findOrderByStatus(status);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            sqlSession.close();
        }
    }

    public OrderDto findOrderById(int id) {
        SqlSession sqlSession = getSqlSession();
        OrderMapper orderMapper = sqlSession.getMapper(OrderMapper.class);
        try {
            return orderMapper.findOrderById(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            sqlSession.close();
        }
    }
}
