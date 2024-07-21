package com.sh.inventory.service;

import com.sh.inventory.model.dao.InventoryMapper;
import com.sh.order.model.dto.OrderDto;
import com.sh.order.model.dto.OrderItemDto;
import com.sh.order.service.OrderService;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

import static com.sh.common.MyBatisTemplate.getSqlSession;

public class InventoryService {
    OrderService orderService = new OrderService();

    public boolean findOrderBook(int orderId) {
        SqlSession sqlSession = getSqlSession();
        InventoryMapper inventoryMapper = sqlSession.getMapper(InventoryMapper.class);
        try {
            OrderDto orderDto = orderService.findOrderById(orderId);

            // 입력한 주문ID에 해당하는 주문이 있는지 판별
            if (orderDto == null) {
                System.out.println("존재하지 않은 주문번호입니다");
                return false;
            }

            List<OrderItemDto> orderItemDtoList = orderDto.getOrderItemList();
            for (OrderItemDto orderItemDto : orderItemDtoList) {
                // 가져온 orderlist에 book_id 조회해서 booK_Id의 inventory가져오기
                // order_item_list에서 가져온 bookId와 quantity만큼 inventory테이블에 update하는 메소드
                inventoryMapper.updateInventory(orderItemDto.getBookId(), orderItemDto.getQuantity());
            }
            // tbl_order에 입력한 주문ID의 status를 '발송완료'로 바꾸는 메소드
            orderService.updateStatus(orderId);

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

