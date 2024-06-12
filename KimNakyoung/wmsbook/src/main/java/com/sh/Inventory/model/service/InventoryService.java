package com.sh.Inventory.model.service;

import com.sh.Inventory.model.dao.InventoryMapper;

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

            // 주문 아이템별로 재고 차감 reduceInventory
            List<OrderItemDto> items = order.getOrderItemList();
            for (OrderItemDto item : items) {
                if(item.getBookId() <= 30){
                    inventoryMapper.reduceInventory(item.getBookId(), item.getQuantity());
                }
                else{
                    System.out.println("아직 등록되지 않은 재고 입니다.");
                    return false ;
                }
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
