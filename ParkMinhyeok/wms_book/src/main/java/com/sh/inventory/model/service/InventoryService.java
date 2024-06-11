package com.sh.inventory.model.service;

import com.sh.inventory.model.dao.InventoryMapper;
import com.sh.order.model.dao.OrderMapper;
import com.sh.order_item.model.dto.OrderItemDto;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

import static com.sh.common.MyBatisTemplate.getSqlSession;

public class InventoryService {

    public static void exWarehousing(int orderId) {
        try (SqlSession sqlSession = getSqlSession()) {
            OrderMapper orderMapper = sqlSession.getMapper(OrderMapper.class);
            InventoryMapper inventoryMapper = sqlSession.getMapper(InventoryMapper.class);

        }
    }
}
