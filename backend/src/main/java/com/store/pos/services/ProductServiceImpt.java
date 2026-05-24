package com.store.pos.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.store.pos.dto.ProductRequestDto;
import com.store.pos.dto.ProductResponseDto;
import com.store.pos.entity.Product;
import com.store.pos.repository.ProductRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductServiceImpt implements ProductService{
    
    private final ProductRepository productRepository;

    @Override
    @Transactional
    public ProductResponseDto createProduct(ProductRequestDto request){
        if(request == null){
            throw new IllegalArgumentException("Request cannot be null!");
        }
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
        if(id == null){
            throw new IllegalArgumentException("ID cannot be null!");
        }
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

    @Override
    @Transactional(readOnly = true)
    public ProductResponseDto updateProduct(Long id, ProductRequestDto request){

        if(id == null){
            throw new IllegalArgumentException("ID cannot be null!");
        }

        Product existingProduct = productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found by ID: " + id));

        existingProduct.setName(request.getName());
        existingProduct.setDescription(request.getDescription());
        existingProduct.setPrice(request.getPrice());
        existingProduct.setQuantity(request.getQuantity());

        Product updateProduct = productRepository.save(existingProduct);

        return mapToResponseDto(updateProduct);
    }

    @Override
    @Transactional(readOnly = true)
    public ProductResponseDto getProductBarcodeById(String barcode){
        if(barcode == null){
            throw new IllegalArgumentException("Barcode should not be null!");
        }

        Product product = productRepository.findByBarcode(barcode).orElseThrow(() -> new RuntimeException("Product not found by Barcode: " + barcode));

        return mapToResponseDto(product);
    }

    @Override
    @Transactional
    public void deleteProduct(Long id){
        if(id == null){
            throw new IllegalArgumentException("ID cannot be null!");
        }
        if(!productRepository.existsById(id)){
            throw new RuntimeException("Cannot delete. Product not found with ID: "+ id);
        }

        productRepository.deleteById(id);
    }

}
