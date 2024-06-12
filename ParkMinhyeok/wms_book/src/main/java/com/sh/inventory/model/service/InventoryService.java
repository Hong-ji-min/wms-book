package com.sh.inventory.model.service;

import com.sh.inventory.model.dao.InventoryMapper;
import com.sh.order.model.dao.OrderMapper;
import com.sh.order.model.dto.OrderDto;
import com.sh.order.model.entity.Status;
import com.sh.order_item.model.dto.OrderItemDto;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

import static com.sh.common.MyBatisTemplate.getSqlSession;

public class InventoryService {

    public static void exWarehousing(int orderId) {
        try (SqlSession sqlSession = getSqlSession()) {
            OrderMapper orderMapper = sqlSession.getMapper(OrderMapper.class);
            InventoryMapper inventoryMapper = sqlSession.getMapper(InventoryMapper.class);

            OrderDto order = orderMapper.findOrderById(orderId);
            if (order.getOrderItemList() == null) {
                throw new RuntimeException("해당 ID로 주문이 존재하지 않습니다." + orderId);
            }

            for (OrderItemDto item : order.getOrderItemList()) {
                inventoryMapper.updateInventory(item.getBookId(), item.getQuantity());
            }

            orderMapper.updateOrderStatus(orderId, Status.발송완료);
            sqlSession.commit();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public static int checkQuantity(int bookId) {
        SqlSession sqlSession = getSqlSession();
        InventoryMapper inventoryMapper = sqlSession.getMapper(InventoryMapper.class);
        return inventoryMapper.checkQuantity(bookId);
    }
}
