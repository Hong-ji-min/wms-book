package com.sh.Inventory.model.dao;

import com.sh.order.model.dao.OrderMapper;
import com.sh.Inventory.model.dto.InventoryDto;
import com.sh.order.model.dto.OrderDto;
import com.sh.order.model.dto.OrderItemDto;
import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.sh.common.MyBatisTemplate.getSqlSession;
import static org.assertj.core.api.Assertions.assertThat;

class InventoryMapperTest {

    SqlSession sqlSession;
    InventoryMapper inventoryMapper;
    OrderMapper orderMapper;  // OrderMapper 추가

    @BeforeEach
    void setUp() {
        // SqlSession 및 Mapper 객체 초기화
        sqlSession = getSqlSession();
        inventoryMapper = sqlSession.getMapper(InventoryMapper.class);
        orderMapper = sqlSession.getMapper(OrderMapper.class); // OrderMapper 초기화
    }

    @AfterEach
    void tearDown() {
        // 항상 롤백하여 테스트 데이터베이스 상태를 초기화
        sqlSession.rollback();
        sqlSession.close();
    }

    @Test
    @DisplayName("재고 감소 테스트")
    void testReduceInventory() {
        int bookId1 = 14; // 첫 번째 책 ID
        int bookId2 = 13; // 두 번째 책 ID
        int reduceQuantity1 = 1; // 첫 번째 책 감소시킬 수량
        int reduceQuantity2 = 1; // 두 번째 책 감소시킬 수량

        // 첫 번째 책의 초기 재고 수량을 가져오기
        InventoryDto initialInventory1 = inventoryMapper.findByBookId(bookId1);
        assertThat(initialInventory1).isNotNull();
        int initialQuantity1 = initialInventory1.getQuantity();

        // 두 번째 책의 초기 재고 수량을 가져오기
        InventoryDto initialInventory2 = inventoryMapper.findByBookId(bookId2);
        assertThat(initialInventory2).isNotNull();
        int initialQuantity2 = initialInventory2.getQuantity();

        // 첫 번째 책의 재고 감소
        inventoryMapper.reduceInventory(bookId1, reduceQuantity1);

        // 두 번째 책의 재고 감소
        inventoryMapper.reduceInventory(bookId2, reduceQuantity2);

        // 감소 후 첫 번째 책의 재고 수량을 가져와서 검증
        InventoryDto updatedInventory1 = inventoryMapper.findByBookId(bookId1);
        assertThat(updatedInventory1).isNotNull();
        assertThat(updatedInventory1.getQuantity()).isEqualTo(initialQuantity1 - reduceQuantity1);

        // 감소 후 두 번째 책의 재고 수량을 가져와서 검증
        InventoryDto updatedInventory2 = inventoryMapper.findByBookId(bookId2);
        assertThat(updatedInventory2).isNotNull();
        assertThat(updatedInventory2.getQuantity()).isEqualTo(initialQuantity2 - reduceQuantity2);
    }
}
