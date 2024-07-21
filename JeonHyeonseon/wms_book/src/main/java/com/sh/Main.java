package com.sh;

import com.sh.book.view.BookView;
import com.sh.user.view.UserView;

public class Main {
    public static void main(String[] args) {
        System.out.println("📕📗 wms 프로그램을 시작합니다 📘📙");
        UserView userView = new UserView();
        userView.loginMenu();
    }
}