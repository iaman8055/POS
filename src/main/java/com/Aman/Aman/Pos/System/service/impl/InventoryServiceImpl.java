package com.Aman.Aman.Pos.System.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.Aman.Aman.Pos.System.Mapper.InventoryMapper;
import com.Aman.Aman.Pos.System.Payload.dto.InventoryDto;
import com.Aman.Aman.Pos.System.Repository.BranchRepository;
import com.Aman.Aman.Pos.System.Repository.InventoryRepository;
import com.Aman.Aman.Pos.System.Repository.ProductRepository;
import com.Aman.Aman.Pos.System.model.Branch;
import com.Aman.Aman.Pos.System.model.Inventory;
import com.Aman.Aman.Pos.System.model.Product;
import com.Aman.Aman.Pos.System.service.InventoryService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class InventoryServiceImpl implements InventoryService {

    private final InventoryRepository inventoryRepository;

    private final BranchRepository branchRepository;

    private final ProductRepository productRepository;
    @Override
    public InventoryDto createInventory(InventoryDto inventoryDto) {
        Branch branch = branchRepository.findById(inventoryDto.getBranchId())
                .orElseThrow(() -> new RuntimeException("Branch not found with ID: " + inventoryDto.getBranchId()));

        Product product = productRepository.findById(inventoryDto.getProductId())
                .orElseThrow(() -> new RuntimeException("Product not found with ID: " + inventoryDto.getProductId()));

        Inventory inventory = InventoryMapper.toEntity(inventoryDto, branch, product);
        inventory.setLastUpdated(LocalDateTime.now());

        Inventory savedInventory = inventoryRepository.save(inventory);
        return InventoryMapper.toDto(savedInventory);
    }

    @Override
    public InventoryDto updateInventory(Long id, InventoryDto inventoryDto) {
        Inventory existingInventory = inventoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Inventory not found with ID: " + id));

        existingInventory.setQuantity(inventoryDto.getQuantity());

        Inventory updatedInventory = inventoryRepository.save(existingInventory);
        return InventoryMapper.toDto(updatedInventory);
    }

    @Override
    public InventoryDto getInventoryById(Long id) {
        Inventory inventory = inventoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Inventory not found with ID: " + id));
        return InventoryMapper.toDto(inventory);
    }

    @Override
    public InventoryDto getInventoryByProductIdandBranchId(Long productId, Long branchId) {
        Inventory inventory=inventoryRepository.findByProductIdAndBranchId(productId,branchId);
        return InventoryMapper.toDto(inventory);
    }

    @Override
    public List<InventoryDto> getAllInventoriesByBranchId(Long branchId) {
        List<Inventory> inventories=inventoryRepository.findByBranchId(branchId);
        return inventories.stream().map(InventoryMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public void deleteInventory(Long id) {
        Inventory inventory = inventoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Inventory not found with ID: " + id));

        inventoryRepository.delete(inventory);
    }
}
