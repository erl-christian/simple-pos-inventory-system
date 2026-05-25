package com.store.pos.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.store.pos.dto.InventoryRequestDto;
import com.store.pos.dto.ProductResponseDto;
import com.store.pos.services.InventoryService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/inventory")
@RequiredArgsConstructor
@CrossOrigin(origins="*")
public class InventoryController {

    private final InventoryService inventoryService;

    @PostMapping("/stock-in")
    public ResponseEntity<ProductResponseDto> stockIn(@RequestBody InventoryRequestDto request){
        ProductResponseDto updateProduct = inventoryService.stockIn(request);
        return ResponseEntity.ok(updateProduct);
    }
}
