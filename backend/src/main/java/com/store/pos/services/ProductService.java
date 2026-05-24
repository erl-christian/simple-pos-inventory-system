package com.store.pos.services;

import java.util.List;

import com.store.pos.dto.ProductRequestDto;
import com.store.pos.dto.ProductResponseDto;

public interface ProductService {
    ProductResponseDto createProduct(ProductRequestDto request);

    ProductResponseDto getProductId(Long id);

    List<ProductResponseDto> getAllProducts();

    ProductResponseDto updateProduct(Long id, ProductRequestDto request);
    ProductResponseDto getProductBarcodeById(String barcode);
    void deleteProduct(Long id);
}  
