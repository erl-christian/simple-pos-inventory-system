package com.store.pos.services;

import com.store.pos.dto.ProductRequestDto;
import com.store.pos.entity.Product;
import com.store.pos.repository.ProductRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import com.store.pos.dto.ProductResponseDto;

@Service
@RequiredArgsConstructor
public class ProductServiceImpt implements ProductService{
    
    private final ProductRepository productRepository;

    @Override
    @Transactional
    public ProductResponseDto createProduct(ProductRequestDto request){
        if(productRepository.existsByBarcode(request.getBarcode())){
            throw new RuntimeException("A product with this barcode already exist");
        }

        Product product = Product.builder().barcode(request.getBarcode()).name(request.getName()).description(request.getDescription()).price(request.getPrice()).quantity(request.getQuantity()).build();


        Product savedProduct = productRepository.save(product);

        return mapToResponseDto(savedProduct);
    }

    @Override
    @Transactional(readOnly = true)
    public ProductResponseDto getProductId(Long id){
        Product product = productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found with the id of " + id));
        return mapToResponseDto(product);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductResponseDto> getAllProducts(){
        return productRepository.findAll().stream().map(this::mapToResponseDto).collect(Collectors.toList());
    }

    private ProductResponseDto mapToResponseDto(Product product){
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
