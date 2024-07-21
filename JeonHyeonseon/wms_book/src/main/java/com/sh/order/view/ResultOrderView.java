package com.sh.order.view;

import com.sh.order.model.dto.OrderDto;

import java.util.List;

public class ResultOrderView {

    public static void displayOrderBook(int result) {
        if (result > 0) {
            System.out.println("📦주문이 완료되었습니다📦");
        } else {
            System.out.println("🧐주문을 실패하였습니다. 다시 한 번 확인해주세요.🧐");
        }
    }
}
