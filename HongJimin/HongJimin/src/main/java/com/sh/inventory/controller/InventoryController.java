package com.sh.inventory.controller;

import com.sh.common.ErrorView;
import com.sh.common.error.ErrorCode;
import com.sh.inventory.model.service.InventoryService;
import com.sh.inventory.view.InventoryResultView;
import com.sh.order.model.entity.Order;
import com.sh.order.model.entity.Status;
import com.sh.order.model.exception.CreateOrderTransactionException;
import com.sh.order.view.OrderResultView;

import java.util.List;


public class InventoryController {
    InventoryService inventoryService = new InventoryService();
    public void findInventory() {
        inventoryService.findInventory();
    }

    public void inboundInventory() {
        inventoryService.inboundInventory();
    }

    public void outboundInventory(Status status) {
        try{
        List<Order> orders = inventoryService.outboundInventory(status);
        OrderResultView.displayOrderStatus(orders, status);
        }catch (CreateOrderTransactionException e) {
            e.printStackTrace();
            ErrorView.displayError(e.getErrorCode());
        } catch (Exception e) {
            ErrorView.displayError(ErrorCode.FIND_ORDER_STATUS_ERROR);
        }
    }

    public void moveInventory() {
        inventoryService.moveInventory();
    }
}
