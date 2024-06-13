package com.sh.inventory.controller;

import com.sh.inventory.model.service.InventoryService;

public class InventoryController {
    private InventoryService inventoryService = new InventoryService();

    public void exWarehousing(int orderId) {
        try {
            InventoryService.exWarehousing(orderId);
            System.out.println("출고처리 완료");
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }

    }
    public int checkQuantity(int bookId) {
        return InventoryService.checkQuantity(bookId);
    }
}
