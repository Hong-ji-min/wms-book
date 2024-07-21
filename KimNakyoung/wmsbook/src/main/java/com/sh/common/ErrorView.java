package com.sh.common;

import com.sh.common.error.ErrorCode;

public class ErrorView {
    public static void displayError(ErrorCode errorCode) {
        System.out.println(errorCode.getMsg());
    }
}
