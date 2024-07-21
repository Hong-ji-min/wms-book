package com.sh.order.model.service;

import com.sh.order.model.dao.OrderMapper;
import com.sh.order.model.entity.Order;
import com.sh.order.model.entity.OrderItem;
import org.apache.ibatis.session.SqlSession;

import static com.sh.common.MyBatisTemplate.getSqlSession;

public class OrderService {

    public int createOrder(Order order) {
        SqlSession sqlSession = getSqlSession();
        OrderMapper orderMapper = sqlSession.getMapper(OrderMapper.class);

        try {
            int result = orderMapper.insertOrder(order);
            for (OrderItem orderItem : order.getOrderItems()) {
                orderItem.setOrderId(order.getOrderId());
                result = orderMapper.insertOrderItem(orderItem);
            }
            sqlSession.commit();
            return result;
        } catch (Exception e) {
            sqlSession.rollback();
            throw new RuntimeException(e);
        } finally {
            sqlSession.close();
        }
    }

    public Order findOrder(int orderId) {
        SqlSession sqlSession = getSqlSession();
        OrderMapper orderMapper= sqlSession.getMapper(OrderMapper.class);


        try {
            return orderMapper.findOrder(orderId);
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
