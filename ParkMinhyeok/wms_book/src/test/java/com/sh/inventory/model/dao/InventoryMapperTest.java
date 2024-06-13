package com.sh.inventory.model.dao;

import com.sh.book.model.dto.BookDto;
import com.sh.inventory.model.dto.InventoryDto;
import com.sh.inventory.model.service.InventoryService;
import com.sh.order.model.dao.OrderMapper;
import com.sh.order.model.dto.OrderDto;
import com.sh.order.model.entity.Status;
import com.sh.order.model.service.OrderService;
import com.sh.order_item.model.dto.OrderItemDto;
import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static com.sh.common.MyBatisTemplate.getSqlSession;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class InventoryMapperTest {
    SqlSession sqlSession;
    InventoryMapper inventoryMapper;
    OrderMapper orderMapper;
    InventoryService inventoryService;
    @BeforeEach
    void setUp() {
        sqlSession = getSqlSession();
        inventoryMapper = sqlSession.getMapper(InventoryMapper.class);
        orderMapper = sqlSession.getMapper(OrderMapper.class);
        inventoryService = new InventoryService();
    }

    @AfterEach
    void tearDown() {
        sqlSession.close();
    }
    @DisplayName("Test exWarehousing")
    @Test
    void exWarehousing() {
        // given
        OrderDto order = orderMapper.findOrderById(11);
        int initailQuantity = inventoryMapper.checkQuantity(27);

        // when
        for (OrderItemDto item : order.getOrderItemList()) {
            inventoryMapper.updateInventory(item.getBookId(), item.getQuantity());
        }
        orderMapper.updateOrderStatus(order.getOrderId(), Status.발송완료);
//        sqlSession.commit();
        // then

        OrderDto updateOrder = orderMapper.findOrderById(order.getOrderId());
        assertThat(updateOrder).isNotNull();
        assertThat(updateOrder.getStatus()).isEqualTo(Status.발송완료);

        int newQuantity = inventoryMapper.checkQuantity(27);
        assertThat(newQuantity).isNotEqualTo(initailQuantity);
    }
}