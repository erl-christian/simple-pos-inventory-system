package com.store.pos.dto;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class ProductResponseDto {
    private Long productId;
    private String barcode;
    private String name;
    private String description;
    private BigDecimal price;
    private Integer quantity;
}
