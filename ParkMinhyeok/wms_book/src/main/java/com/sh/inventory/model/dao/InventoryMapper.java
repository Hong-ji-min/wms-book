package com.sh.inventory.model.dao;

import com.sh.book.model.dto.BookDto;
import org.apache.ibatis.annotations.Param;

public interface InventoryMapper {
    void updateInventory(@Param("bookId") int bookId, @Param("quantity") int quantity);

    int checkQuantity(int bookId);
}
