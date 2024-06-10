package com.sh.common.error;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor


public enum ErrorCode {
    FIND_All_BOOK_ERROR("도서 전체 조회 중 오류가 발생했습니다."),
    FIND_A_BOOK_ERROR("한 권의 도서 조회 중 오류가 발생했습니다."),
    FIND_BOOK_BY_TITLE_ERROR("책 제목 조회 중 오류가 발생했습니다"),
    FIND_BOOK_BY_AUTHOR_ERROR("저자 조회 중 오류가 발생했습니다."),
    FIND_BOOK_BY_CATEGORY_ERROR("카테고리 조회 중 오류가 발생했습니다."),
    FIND_BOOK_BY_PRICE_ERROR("가격 조회 중 오류가 발생했습니다."),
    CREATE_ORDER_ERROR("도서 주문서 등록 중 오류가 발생했습니다."),
    CREATE_SELECT_ORDER_ERROR("주문 목록 조회 중 오류가 발생했습니다."),
    FIND_ORDER_STATUS_ERROR("주문상태별 조회 중 오류가 발생했습니다."), FIND_ORDER_BY_ORDERID_ERROR("주문 아이디로 조회중 오류가 발생했습니다.");

    @Getter
    final String msg;
}
