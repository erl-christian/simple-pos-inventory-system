
package com.store.pos.services;

import com.store.pos.dto.InventoryRequestDto;
import com.store.pos.dto.ProductResponseDto;

public  interface InventoryService {

    ProductResponseDto stockIn(InventoryRequestDto request);
    
}