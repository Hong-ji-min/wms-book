package com.sh.inventory.model.dto;

import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor // 기본생성자
@AllArgsConstructor // 모든 매개인자 생성자
@Getter
@Setter
@ToString

public class InventoryDto {
    private int inventoryId;
    private int bookId;
    private int quantity;
    private String location;
    private LocalDateTime lastUpdated;
}
