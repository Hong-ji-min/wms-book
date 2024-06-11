package com.sh.order.view;

public class OrderResultView {
    public static void displayResult(int result) {
        if(result > 0) {
            System.out.println("> 🎉🎉🎉" + "주문 등록 성공 🎉🎉🎉");
        }
        else {
            System.out.println("> 😭😭😭" + "주문 등록 실패 😭😭😭");
        }
    }
}
