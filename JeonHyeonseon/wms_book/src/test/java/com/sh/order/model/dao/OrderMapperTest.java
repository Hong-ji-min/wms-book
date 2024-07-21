package com.sh.order.model.dao;

import com.sh.order.model.dto.OrderDto;
import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static com.sh.common.MyBatisTemplate.getSqlSession;
import static com.sh.order.model.dto.Status.배송준비중;
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

    @DisplayName("1건의 도서 주문을 등록한다.(주문 아이템 없음)")
    @Test
    void insertOrder() {
        // given
        OrderDto orderDto = new OrderDto();
        orderDto.setOrdererName("김철수");
        orderDto.setOrdererAddress("서울시 강남구 역삼동 123-45");
        orderDto.setOrderDate(LocalDateTime.of(2024, 5, 29, 14, 5, 10));
        orderDto.setStatus(배송준비중);

        // when
        int result = orderMapper.insertOrder(orderDto);
        System.out.println(result);

        // then
        assertThat(orderDto).isNotNull();
        assertThat(orderDto.getOrdererName()).isEqualTo("김철수");
        assertThat(orderDto.getOrdererAddress()).isEqualTo("서울시 강남구 역삼동 123-45");
        assertThat(orderDto.getStatus()).isEqualTo(배송준비중);
    }

    @DisplayName("1건의 도서 아이템을 등록한다.")
    @Test
    void insertOrderItem() {
        // given

    }
}