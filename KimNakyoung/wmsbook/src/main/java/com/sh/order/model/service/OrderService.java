package com.sh.order.model.service;

import com.sh.common.error.ErrorCode;
import com.sh.order.model.dto.Status;
import com.sh.order.model.exception.CreateOrderTransactionException;
import com.sh.order.model.dao.OrderMapper;
import com.sh.order.model.dto.OrderDto;
import com.sh.order.model.dto.OrderItemDto;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

import static com.sh.common.MyBatisTemplate.getSqlSession;

public class OrderService {
    public int createOrder(OrderDto orderDto) throws CreateOrderTransactionException {
        SqlSession sqlSession = getSqlSession();
        OrderMapper orderMapper = sqlSession.getMapper(OrderMapper.class);

        // my-batis-config.xml를 가서
        //<mappers>
        //        <mapper resource="sql/menu/menu-mapper.xml"/>
        //        <mapper resource="sql/order/order-mapper.xml"/>
        //    </mappers>
        // 여기다가 경로 넣고
        // 그 경로에 order-mapper.xml 만들어서 쿼리 넣기 양식은 마이바티스 페이지에 있다 (노션 참고)



        try {
            // 트랜잭션 처리코드
            // 1건의 주문 등록
            int result = orderMapper.insertOrder(orderDto);
            System.out.println(orderDto.getOrderId());
            // n건의 주문메뉴 등록
            for(OrderItemDto orderItemDto : orderDto.getOrderItemList()){
                // 발급된 주문코드를 OrderMenuDto#orderCode 대입
                orderItemDto.setOrderId(orderDto.getOrderId());
                result = orderMapper.insertOrderItem(orderItemDto);
            }
            sqlSession.commit();
            return result;
        } catch (Exception e) {
            sqlSession.rollback();
            throw new CreateOrderTransactionException(ErrorCode.CREATE_ORDER_ERROR, e);
        } finally {
            sqlSession.close();
        }
    }

    public List<OrderDto> findOrdersByStatus(String status) {
        SqlSession sqlSession = getSqlSession();
        OrderMapper orderMapper = sqlSession.getMapper(OrderMapper.class);
        try {
            return orderMapper.findOrdersByStatus(status);
        } catch (Exception e) {
            sqlSession.rollback();
            throw new RuntimeException(e);
        } finally {
            sqlSession.close();
        }
    }

    public OrderDto findOrderById(int orderId) {
        SqlSession sqlSession = getSqlSession();
        OrderMapper orderMapper = sqlSession.getMapper(OrderMapper.class);
        try {
            return orderMapper.findOrderById(orderId);
        } catch (Exception e) {
            sqlSession.rollback();
            throw new RuntimeException(e);
        } finally {
            sqlSession.close();
        }
    }

    // 주문 상태를 발송완료로 변경 updateOrderStatus

    public void updateOrderStatus(int orderId, Status status) {
        SqlSession sqlSession = getSqlSession();
        OrderMapper orderMapper = sqlSession.getMapper(OrderMapper.class);
        try {
            orderMapper.updateOrderStatus(orderId, status.toString());
            sqlSession.commit();
        } catch (Exception e) {
            sqlSession.rollback();
            throw new RuntimeException(e);
        } finally {
            sqlSession.close();
        }
    }
}
