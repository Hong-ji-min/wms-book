package com.sh.inventory.model.dao;

import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.sh.common.MyBatisTemplate.getSqlSession;

class InventoryMapperTest {
    SqlSession sqlSession;
    InventoryMapper inventoryMapper;

    @BeforeEach
    void setUp() {
        this.sqlSession = getSqlSession();
        this.inventoryMapper = this.sqlSession.getMapper(InventoryMapper.class);

    }

    @AfterEach
    void tearDown() {
        this.sqlSession.close();
    }

    @DisplayName("재고수량 수정")
    @Test
    void updateInventory() {
        int bookId = 1;
        int quantity = 2;
        inventoryMapper.updateInventory(bookId,quantity);
    }


    @Test
    void changeStatus() {
    }
}