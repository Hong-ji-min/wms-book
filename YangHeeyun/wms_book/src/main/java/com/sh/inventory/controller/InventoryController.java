package com.sh.inventory.controller;

import com.sh.inventory.service.InventoryService;

public class InventoryController {
    InventoryService inventoryService = new InventoryService();

    public boolean findOrderBook(int orderId) {
        return inventoryService.findOrderBook(orderId);
    }
}
