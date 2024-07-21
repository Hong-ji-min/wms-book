package com.sh.order.view;

public class OrderResultView {

    public static void displayResult(String type, int result) {
        if(result > 0) {
            System.out.println("> 🎉🎉🎉" + type + " 성공 🎉🎉🎉");
        }
        else {
            System.out.println("> 😭😭😭" + type + " 실패 😭😭😭");
        }
    }
}