package com.sh.order.model.dao;

import com.sh.*;
import com.sh.order.model.entity.Order;
import com.sh.order.model.entity.OrderItem;
import com.sh.order.model.entity.Status;
import org.apache.ibatis.session.SqlSession;
import org.assertj.core.internal.IterableElementComparisonStrategy;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static com.sh.common.MyBatisTemplate.getSqlSession;
import static org.assertj.core.api.Assertions.as;
import static org.assertj.core.api.Assertions.assertThat;


class OrderMapperTest {
    SqlSession sqlSession = getSqlSession();
    OrderMapper orderMapper = sqlSession.getMapper(OrderMapper.class);

    @BeforeEach
    void setUp() {
        this.sqlSession = getSqlSession();
        this.orderMapper = this.sqlSession.getMapper(OrderMapper.class);
    }

    @AfterEach
    void tearDown() {
        this.sqlSession.close();
    }

    @DisplayName("한건의 주문 등록")
    @Test
    void testInsertOrder() {
        //given
        Order order = new Order(0, "홍길동", "서울시 강동구 풍납동 1234-56",  LocalDateTime.now(), Status.배송준비중);

        //when
        int count = orderMapper.insertOrder(order);
        System.out.println(count);

        //then
        assertThat(count).isNotZero().isPositive();

    }

    @DisplayName("한건의 주문에 여러 아이템 등록")
    @Test
    void testInsertOrderItem() {
        //given
        Order order = new Order( 0, "아이고옹", "테스트 배송지", null, Status.주문확인중);
        List<OrderItem> orderItemList = new ArrayList<>();
        orderItemList.add(order.getOrderItemList().get(0));

        //when
        int orderItemResult = orderMapper.insertOrderItem(order.getOrderItemList().get(0));
        System.out.println(orderItemResult);

        //then
        assertThat(orderItemResult).isNotZero().isPositive();
    }

    @DisplayName(" 한번에 주문정보/주문아이템 정보를 입력")
    @Test
    void findOrderItem() {
        //given
        int orderId = 1;

        //when
        Order order = orderMapper.findOrderItem(orderId);
        System.out.println(order);

        //then
        assertThat(order).isNotNull();
        assertThat(order.getOrderItemList()).isNotNull().isNotEmpty();

    }

}