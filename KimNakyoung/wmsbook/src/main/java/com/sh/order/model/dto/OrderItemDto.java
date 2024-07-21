package com.sh.order.model.dto;

import com.sh.book.model.dto.BookDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor // 마이바티스 쓸려면 기본생성자 필수
@AllArgsConstructor
public class OrderItemDto {
    private int orderItemId; // 같은 주문에도 여러개의 주문이 있을 수도 있기때문에 넣은것.
    //주문 항목 번호 이 번호는 각 책의 주문을 고유하게 식별하는 데 사용
    private int orderId; // 주문코드
    private int bookId; // 도서코드
    private int quantity; // 수량
    private BookDto bookdto;
}
