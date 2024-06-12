package com.sh.inventory.model.service;

import com.sh.inventory.model.dao.InventoryMapper;
import com.sh.inventory.model.dto.InventoryDto;
import com.sh.order.model.dao.OrderMapper;
import com.sh.order.model.dto.OrderDto;
import com.sh.order.model.dto.OrderItemDto;
import com.sh.order.model.dto.OrderableStatus;
import org.apache.ibatis.session.SqlSession;
import static com.sh.common.MyBatisTemplate.getSqlSession;
import java.util.List;

public class InventoryService {

    /**
     * 주문확인중 상태의 모든 주문을 조회한다.
     *
     * @return 주문확인중 상태의 주문 목록
     */
    public List<OrderDto> getPendingOrdersWithInventory() {
        SqlSession sqlSession = getSqlSession();
        OrderMapper orderMapper = sqlSession.getMapper(OrderMapper.class);
        // 주문확인중 상태의 주문 목록 조회
        List<OrderDto> orders = orderMapper.selectOrdersByStatus(OrderableStatus.valueOf(OrderableStatus.주문확인중.name()));
        sqlSession.close();
        return orders;
    }

    /**
     * 특정 주문을 출고 처리합니다. 주문의 상태가 '주문확인중'일 경우에만 출고 처리되며,
     * 출고 후 재고가 차감되고, 모든 아이템이 정상적으로 출고된 경우 주문의 상태가 '발송완료'로 변경된다.
     *
     * @param orderId 출고할 주문 ID
     * @throws Exception 출고 처리 중 발생한 예외
     */
    public void processShipment(int orderId) throws Exception {
        SqlSession sqlSession = getSqlSession();
        OrderMapper orderMapper = sqlSession.getMapper(OrderMapper.class);
        InventoryMapper inventoryMapper = sqlSession.getMapper(InventoryMapper.class);
        try {
            // 주문 ID로 주문 조회
            OrderDto order = orderMapper.selectOrderById(orderId);
            System.out.println("Order Status: " + order.getStatus());
            // 주문 상태가 '주문확인중'인지 확인
            if (order != null && order.getStatus().equals(OrderableStatus.valueOf(OrderableStatus.주문확인중.name()))) {
                // 각 주문 아이템에 대해 재고 차감 처리
                for (OrderItemDto item : order.getOrderItems()) {
                    List<InventoryDto> inventories = inventoryMapper.selectInventoryByBookId(item.getBookId());
                    int quantityToDecrement = item.getQuantity();
                    for (InventoryDto inventory : inventories) {
                        if (quantityToDecrement <= 0) break;
                        if (inventory.getQuantity() >= quantityToDecrement) {
                            inventory.setQuantity(inventory.getQuantity() - quantityToDecrement);
                            inventoryMapper.updateInventoryQuantity(inventory);
                            quantityToDecrement = 0;
                        } else {
                            quantityToDecrement -= inventory.getQuantity();
                            inventory.setQuantity(0);
                            inventoryMapper.updateInventoryQuantity(inventory);
                        }
                    }
                    // 재고가 부족한 경우 예외 발생
                    if (quantityToDecrement > 0) {
                        throw new Exception("재고가 부족합니다.");
                    }
                }
                // 모든 아이템이 정상적으로 출고된 경우 주문 상태를 '발송완료'로 변경
                order.setStatus(OrderableStatus.valueOf(OrderableStatus.발송완료.name()));
                orderMapper.updateOrderStatus(order);
                sqlSession.commit();
            } else {
                throw new Exception("유효하지 않은 주문 상태입니다.");
            }
        } catch (Exception e) {
            sqlSession.rollback();
            System.out.println("Exception: " + e.getMessage());
            throw e;
        } finally {
            sqlSession.close();
        }
    }
}
