package com.sh.order.model.dto;

// 1. 주문 생성 (고객 주문 생성 (주문 번호, 고객 정보, 주문 도서, 수량, 배송지 정보 등))
// 2. 주문 처리 ("주문 상태 관리 (주문 접수, 처리 중, 배송 중, 완료 등)")

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.List;


@Data
@NoArgsConstructor // 마이바티스 쓸려면 기본생성자 필수
@AllArgsConstructor
public class OrderDto {
    private int orderId; // 주문번호
    private String orderName; //고객 정보
    private String orderAddress; // 배송지 주소
    private Timestamp orderDate; // 주문 일자
    private Status status; // 주문 상태 관리 enum // 주문처리때 쓸듯
    // enum("주문확인중,"배송준비중","발송완료","배송중","배송완료","주문취소")

    /**
     *
     * PK 넣기 컬렉션 리스트 사용 ( 1:N관계 객체지향에서는 이렇게 표현)
     *
     */

    private List<OrderItemDto> orderItemList;







}
