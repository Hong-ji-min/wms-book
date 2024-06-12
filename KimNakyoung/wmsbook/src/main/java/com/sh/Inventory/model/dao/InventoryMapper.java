package com.sh.Inventory.model.dao;

import org.apache.ibatis.annotations.Param;

public interface InventoryMapper {
//    InventoryDto ObInventory(int orderId) ;
//    InventoryDto selectOrderById(int orderId);
//    void updateOrderStatus(int orderId);
    void reduceInventory(@Param("bookId") int bookId, @Param("quantity") int quantity);


}
