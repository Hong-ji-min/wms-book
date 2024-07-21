package com.sh.order.model.service;

import com.sh.order.model.dao.OrderMapper;
import com.sh.order.model.dto.OrderDto;
import com.sh.order.model.dto.OrderItemDto;
import com.sh.order.model.dto.OrderableStatus;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

import static com.sh.common.MyBatisTemplate.getSqlSession;

public class OrderService {

    public void registerOrder(OrderDto order) {
        SqlSession sqlSession = getSqlSession();
        OrderMapper orderMapper = sqlSession.getMapper(OrderMapper.class);
        try {
            // 주문 등록
            order.setStatus(OrderableStatus.주문확인중); // 초기 상태 설정
            orderMapper.insertOrder(order);
            int orderId = order.getOrderId();

            // 주문 아이템 등록
            for (OrderItemDto item : order.getOrderItems()) {
                item.setOrderId(orderId);
                orderMapper.insertOrderItem(item);
            }

            sqlSession.commit();
        } catch (Exception e) {
            sqlSession.rollback();
            throw new RuntimeException(e);
        } finally {
            sqlSession.close();
        }
    }
    public List<OrderDto> getOrdersByStatus(OrderableStatus status) {
        try (SqlSession sqlSession = getSqlSession()) {
            OrderMapper orderMapper = sqlSession.getMapper(OrderMapper.class);
            return orderMapper.selectOrdersByStatus(status);
        } catch (Exception e) {
            throw new RuntimeException("주문상태로 주문 조회 실패", e);
        }
    }

    public OrderDto getOrderById(int orderId) {
        try (SqlSession sqlSession = getSqlSession()) {
            OrderMapper orderMapper = sqlSession.getMapper(OrderMapper.class);
            return orderMapper.selectOrderById(orderId);
        } catch (Exception e) {
            throw new RuntimeException("ID로 주문 조회 실패", e);
        }
    }
}
