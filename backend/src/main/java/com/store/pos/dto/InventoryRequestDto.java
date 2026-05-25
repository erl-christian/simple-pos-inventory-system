package com.store.pos.dto;

import lombok.Data;

@Data
public class InventoryRequestDto {
    private String barcode;
    private Integer quantity;
    private String remarks;
}
