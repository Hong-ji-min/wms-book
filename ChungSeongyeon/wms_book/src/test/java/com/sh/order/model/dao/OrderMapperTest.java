package com.sh.order.model.dao;

import com.sh.order.model.dto.OrderDto;
import com.sh.order.model.dto.OrderableStatus;
import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.sh.common.MyBatisTemplate.getSqlSession;
import static org.assertj.core.api.Assertions.assertThat;

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

    @DisplayName("주문을 추가할 수 있다.")
    @Test
    void testInsertOrder() {
        // given
        OrderDto orderDto = new OrderDto();
        orderDto.setOrdererName("John Doe");
        orderDto.setOrdererAddress("123 Main St");
        orderDto.setStatus(OrderableStatus.valueOf("주문확인중"));

        // when
        int result = orderMapper.insertOrder(orderDto);
        sqlSession.commit();

        // then
        assertThat(result).isEqualTo(1);
        assertThat(orderDto.getOrderId()).isNotNull();
    }

    @DisplayName("주문 상태별로 조회할 수 있다.")
    @Test
    void testSelectOrdersByStatus() {
        // given
        OrderableStatus status = OrderableStatus.주문확인중;

        // when
        List<OrderDto> orders = orderMapper.selectOrdersByStatus(status);

        // then
        assertThat(orders).isNotNull().isNotEmpty();
        orders.forEach(order -> {
            assertThat(order.getStatus()).isEqualTo(status);
        });
    }

    @DisplayName("특정 주문번호로 주문과 주문 아이템을 조회할 수 있다.")
    @Test
    void testSelectOrderById() {
        // given
        int orderId = 1;

        // when
        OrderDto order = orderMapper.selectOrderById(orderId);

        // then
        assertThat(order).isNotNull();
        assertThat(order.getOrderId()).isEqualTo(orderId);
        assertThat(order.getOrderItems()).isNotNull().isNotEmpty();
    }
}
