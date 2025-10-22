package com.Aman.Aman.Pos.System.service;

import com.Aman.Aman.Pos.System.Payload.dto.InventoryDto;
import java.util.List;

public interface InventoryService {

    InventoryDto createInventory(InventoryDto inventoryDto);

    InventoryDto updateInventory(Long id, InventoryDto inventoryDto);

    InventoryDto getInventoryById(Long id);

    InventoryDto getInventoryByProductIdandBranchId(Long productId,Long branchId);

    List<InventoryDto> getAllInventoriesByBranchId(Long branchId);

    void deleteInventory(Long id);
}
