package com.sh.common.error;

import lombok.Getter;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor // 기본생성자
public enum ErrorCode {

    CREATE_ORDER_ERROR("주문중 오류가 발생했습니다."),
    USER_INVALID_INPUT_ERROR("사용자 입력값이 유효하지 않습니다.");


    @Getter // 필드레벨에 작성하면 특정 필드만 getter 생성
    final String msg;


//    ErrorCode(String msg) {
//        this.msg = msg;
//    } 롬곡으로 만듦 기본생성자 필요함



}
