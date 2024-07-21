package com.sh.inventory.controller;

import com.sh.inventory.model.service.InventoryService;
import com.sh.order.model.dto.OrderDto;
import java.util.List;

public class InventoryController {
    private final InventoryService inventoryService = new InventoryService();

    public List<OrderDto> getPendingOrdersWithInventory() {
        return inventoryService.getPendingOrdersWithInventory();
    }

    public boolean processShipment(int orderId) {
        try {
            inventoryService.processShipment(orderId);
            return true;
        } catch (Exception e) {
            System.out.println("Error processing shipment: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
}
