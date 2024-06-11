package com.sh.inventory.model.dao;

import org.apache.ibatis.annotations.Param;

public interface InventoryMapper {

    // 출고된 책 종류와 수에 따라 inventory에서 빼주기
    void updateInventory(@Param("bookId") int bookId, @Param("quantity") int quantity);

}
