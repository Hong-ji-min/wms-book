package com.sh.Inventory.model.dao;

import com.sh.Inventory.model.dto.InventoryDto;
import org.apache.ibatis.annotations.Param;

public interface InventoryMapper {
//    InventoryDto ObInventory(int orderId) ;
//    InventoryDto selectOrderById(int orderId);
//    void updateOrderStatus(int orderId);
    void reduceInventory(@Param("bookId") int bookId, @Param("quantity") int quantity);


    InventoryDto findByBookId(int bookId);
}
