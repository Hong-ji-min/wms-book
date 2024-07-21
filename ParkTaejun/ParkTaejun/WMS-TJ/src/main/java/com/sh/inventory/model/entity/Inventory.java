package com.sh.inventory.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Inventory {

    private int inventoryId;

    private int bookId;
    private int quantity;
    private String location;
    private Timestamp lastUpdated;
}
