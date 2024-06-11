package com.sh.inventory.controller;

import com.sh.inventory.model.service.InventoryService;

public class InventoryController {
    private InventoryService inventoryService = new InventoryService();

    public void exWarehousing(int orderId) {
        InventoryService.exWarehousing(orderId);
    }
}
