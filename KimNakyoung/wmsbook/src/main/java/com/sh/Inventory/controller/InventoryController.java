package com.sh.Inventory.controller;

import com.sh.Inventory.model.service.InventoryService;


public class InventoryController {

    private InventoryService inventoryService = new InventoryService();

    // 주문번호로 재고 출고 처리하기
    public boolean ObInventory(int orderId) {
        return inventoryService.ObInventory(orderId);
    }
}
