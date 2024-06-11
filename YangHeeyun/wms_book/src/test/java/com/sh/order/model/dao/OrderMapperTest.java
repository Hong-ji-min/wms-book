package com.sh.order.model.dao;

import com.sh.order.model.dto.OrderDto;
import com.sh.order.model.dto.OrderItemDto;
import com.sh.order.model.dto.Status;
import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.sh.common.MyBatisTemplate.getSqlSession;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

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
        this.sqlSession.rollback();
        this.sqlSession.close();
    }

    @DisplayName("주문 정보 생성")
    @Test
    void testInsertOrder() {
        // given
        OrderDto orderDto = new OrderDto();
        orderDto.setOrdererName("양희윤");
        orderDto.setOrdererAddress("경기도 화성시");
        orderDto.setStatus(Status.주문확인중);

        // when
        int result = orderMapper.insertOrder(orderDto);  // tbl_order에 주문 추가해 order_id 생성하기
        sqlSession.commit();

        // then
        assertThat(result).isEqualTo(1);
        int orderId = orderDto.getOrderId(); // 등록된 행의 pk컬럼 가져오기
        System.out.println("OrderId = " + orderId);
    }

    @DisplayName("주문 목록 생성")
    @Test
    void testInsertOrderItem() {
        // given
        OrderDto orderDto = new OrderDto();
        orderDto.setOrdererName("양희윤");
        orderDto.setOrdererAddress("경기도 화성시");
        orderDto.setStatus(Status.주문확인중);

        int result = orderMapper.insertOrder(orderDto); // tbl_order에 주문 추가해 order_id 생성하기
        assertThat(result).isEqualTo(1);
        int orderId = orderDto.getOrderId(); // 등록된 행의 pk컬럼 가져오기

        OrderItemDto orderItemDto = new OrderItemDto();
        orderItemDto.setOrderId(orderId);
        orderItemDto.setBookId(1);
        orderItemDto.setQuantity(4);

        // when
        int result2 = orderMapper.insertOrderItem(orderItemDto);
        sqlSession.commit();

        // Then
        assertThat(result2).isEqualTo(1);
        assertNotNull(orderItemDto.getOrderItemId());
        System.out.println("OrderItem ID: " + orderItemDto.getOrderItemId());
    }

    @DisplayName("주문번호로 주문 정보 조회")
    @Test
    void testFindOrderById() {
        // given
        int orderId = 1;
        // when
        OrderDto order = orderMapper.findOrderById(orderId);
        // Then
        assertThat(order).isNotNull();
        assertThat(order.getOrderId()).isEqualTo(orderId);
        assertThat(order.getOrderItemList()).isNotNull().isNotEmpty();
    }

    @DisplayName("주문번호로 주문 정보 조회")
    @Test
    void findOrderByStatus() {
        // given
        Status status = Status.valueOf("주문확인중");
        // when
        List<OrderDto> orderList = orderMapper.findOrderByStatus(status);
        // Then
        assertThat(orderList).isNotNull();
        System.out.println(orderList);
    }
}