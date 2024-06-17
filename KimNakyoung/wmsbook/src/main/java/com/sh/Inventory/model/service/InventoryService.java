package com.sh.Inventory.model.service;

import com.sh.Inventory.model.dao.InventoryMapper;

import com.sh.Inventory.model.dto.InventoryDto;
import com.sh.order.model.dto.OrderDto;
import com.sh.order.model.dto.OrderItemDto;
import com.sh.order.model.dto.Status;
import com.sh.order.model.service.OrderService;
import org.apache.ibatis.session.SqlSession;


import java.util.List;

import static com.sh.common.MyBatisTemplate.getSqlSession;

public class InventoryService {
    private OrderService orderService = new OrderService();

    public boolean ObInventory(int orderId) {
        SqlSession sqlSession = getSqlSession();
        InventoryMapper inventoryMapper = sqlSession.getMapper(InventoryMapper.class);
        try {
            // 주문 정보 조회
            OrderDto order = orderService.findOrderById(orderId);
            if (order == null || !order.getStatus().equals(Status.ORDER_CONFIRMING)) {
                return false;
            }

            // 주문 아이템별로 재고 확인 및 차감
            List<OrderItemDto> items = order.getOrderItemList();
            for (OrderItemDto item : items) {
                InventoryDto inventory = inventoryMapper.findByBookId(item.getBookId());
                if (inventory == null || inventory.getQuantity() < item.getQuantity()) {
                    System.out.println("재고가 부족하거나 등록되지 않은 책입니다.");
                    return false;
                }
            }

            // 재고 차감
            for (OrderItemDto item : items) {
                inventoryMapper.reduceInventory(item.getBookId(), item.getQuantity());
            }


            // 주문 상태를 발송완료로 변경 updateOrderStatus
            orderService.updateOrderStatus(orderId, Status.SHIPPED);

            sqlSession.commit();
            return true;
        } catch (Exception e) {
            sqlSession.rollback();
            throw new RuntimeException(e);
        } finally {
            sqlSession.close();
        }
    }
}
