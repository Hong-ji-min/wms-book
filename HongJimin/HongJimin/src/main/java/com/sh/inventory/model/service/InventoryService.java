package com.sh.inventory.model.service;

import com.sh.common.error.ErrorCode;
import com.sh.inventory.model.dao.InventoryMapper;
import com.sh.inventory.model.entity.Inventory;
import com.sh.order.model.dao.OrderMapper;
import com.sh.order.model.entity.Order;
import com.sh.order.model.entity.Status;
import com.sh.order.model.exception.CreateOrderTransactionException;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

import static com.sh.common.MyBatisTemplate.getSqlSession;

public class InventoryService {
    public void findInventory() {
    }

    public void inboundInventory() {
    }

    public List<Order> outboundInventory(Status status) {
        SqlSession sqlSession = getSqlSession();
        OrderMapper orderMapper = sqlSession.getMapper(OrderMapper.class);
        InventoryMapper inventoryMapper = sqlSession.getMapper(InventoryMapper.class);
        try{
            List<Order> orders = orderMapper.findByOrderStatus(status);
            sqlSession.commit();
            return orders;
        } catch (Exception e) {
            sqlSession.rollback();
            throw new CreateOrderTransactionException(ErrorCode.OUTBOUND_ERROR, e);
        } finally {
            sqlSession.close();
        }
    }

    public void moveInventory() {
    }
}
