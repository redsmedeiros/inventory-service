package com.inventoryService.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.inventoryService.Dto.InventoryDto;
import com.inventoryService.repository.InventoryRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class InventoryService {

    private final InventoryRepository inventoryRepository;
    
    @Transactional(readOnly = true)
    public List<InventoryDto> isInStock(List<String> skuCode){
        
        return inventoryRepository.findBySkuCodeIn(skuCode).stream().map(inventory -> 
            InventoryDto.builder()
                .skuCode(inventory.getSkuCode())
                .isInStock(inventory.getQuantity() > 0)
                .build()
        ).toList();
    }
}
