package com.sh.order.model.dao;

import com.sh.order.model.dto.OrderDto;
import com.sh.order.model.entity.Status;
import com.sh.order_item.model.dto.OrderItemDto;
import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static com.sh.common.MyBatisTemplate.getSqlSession;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class OrderMapperTest {
    SqlSession sqlSession;
    OrderMapper orderMapper;

    @BeforeEach
    void setUp() {
        this.sqlSession = getSqlSession();
        this.orderMapper = this.sqlSession.getMapper(OrderMapper.class);
    }

    @AfterEach
    void tearDown() {
            this.sqlSession.close();
    }

    @Test
    void insertOrder() {
        // given
        OrderDto order = new OrderDto();
        order.setOrdererName("박민혁");
        order.setOrdererAddress("용인시 기흥구");
        order.setOrderDate(LocalDateTime.now());
        order.setStatus(Status.주문확인중);

        // when
        int result = orderMapper.insertOrder(order);
//        sqlSession.commit();

        // then
        assertEquals(1, result);
        assertNotNull(order.getOrderId());
        System.out.println("Order ID : " + order.getOrderId());
    }

    @Test
    void insertOrderItem() {
        // Given
        OrderDto order = new OrderDto();
        order.setOrdererName("박민혁");
        order.setOrdererAddress("용인시 기흥구");
        order.setOrderDate(LocalDateTime.now());
        order.setStatus(Status.주문확인중);

        // 먼저 주문을 삽입하여 order_id를 생성
        int orderResult = orderMapper.insertOrder(order);
//        sqlSession.commit();
        assertEquals(1, orderResult);
        assertNotNull(order.getOrderId());

        OrderItemDto orderItem = new OrderItemDto();
        orderItem.setOrderId(order.getOrderId());
        orderItem.setBookId(1);
        orderItem.setQuantity(2);

        // When
        int result = orderMapper.insertOrderItem(orderItem);
//        sqlSession.commit();

        // Then
        assertEquals(1, result);
        assertNotNull(orderItem.getOrderItemId());
        System.out.println("Order Item ID: " + orderItem.getOrderItemId());
    }
    @DisplayName("Test find order by status")
    @Test
    void findOrderByStatus() {
        // given
        Status status = Status.valueOf("주문확인중");
        // when
        List<OrderDto> orderByStatusList = orderMapper.findOrderByStatus(status);
        // then
        assertThat(orderByStatusList).isNotNull();
        for (OrderDto order : orderByStatusList) {
            System.out.println(order);
        }
    }

    @DisplayName("Test find order by id")
    @Test
    void findOrderById() {
        // given
        int id = 1;
        // when
        OrderDto order = orderMapper.findOrderById(id);
        // then
        assertThat(order).isNotNull();
        assertThat(order.getOrderId()).isEqualTo(id);
        assertThat(order.getOrderItemList()).isNotNull().isNotEmpty();
//        System.out.println(order);
    }
}
