package com.sh.order.model.entity;



import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Order {

    private int orderId;
    private String orderName;
    private String orderAddress;
    private Timestamp orderDate;
    private Status status;
    private List<OrderItem> orderItems;

}
