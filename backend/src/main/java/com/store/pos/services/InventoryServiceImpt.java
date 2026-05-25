package com.store.pos.services;

import org.springframework.stereotype.Service;

import com.store.pos.dto.InventoryRequestDto;
import com.store.pos.dto.ProductResponseDto;
import com.store.pos.entity.InventoryTransaction;
import com.store.pos.entity.Product;
import com.store.pos.repository.InventoryTransactionsRepository;
import com.store.pos.repository.ProductRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class InventoryServiceImpt implements InventoryService{

    private final ProductRepository productRepository;
    private final InventoryTransactionsRepository inventoryTransactionsRepository;

    @Override
    @Transactional
    public ProductResponseDto stockIn(InventoryRequestDto request){
        Product product = productRepository.findByBarcode(request.getBarcode()).orElseThrow(() -> new RuntimeException("Cannot add stock. Product not Found!" + request.getBarcode()));

        if (request.getQuantity() <= 0) {
            throw new RuntimeException("Stock-in quantity must be greater than zero.");
        }

        int newQuantity = product.getQuantity() + request.getQuantity();
        product.setQuantity(newQuantity);
        productRepository.save(product);

        InventoryTransaction transaction = InventoryTransaction.builder().product(product).type("stock_in").quantity(request.getQuantity()).remarks(request.getRemarks()).build();

        if(transaction == null){
            throw new IllegalArgumentException("Transaction cannot be null!");
        }

        inventoryTransactionsRepository.save(transaction);

        return mapToProductResponseDto(product);
    }

    private ProductResponseDto mapToProductResponseDto(Product product){
        ProductResponseDto dto = new ProductResponseDto();
        dto.setProductId(product.getProductId());
        dto.setBarcode(product.getBarcode());
        dto.setName(product.getName());
        dto.setDescription(product.getDescription());
        dto.setPrice(product.getPrice());
        dto.setQuantity(product.getQuantity());

        return dto;
    }
}
