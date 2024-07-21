package com.sh.inventory.model.dao;

import com.sh.inventory.model.dto.InventoryDto;
import java.util.List;

public interface InventoryMapper {
    List<InventoryDto> selectInventoryByBookId(int bookId);
    void updateInventoryQuantity(InventoryDto inventory);
}
