package com.Aman.Aman.Pos.System.Controller;

import com.Aman.Aman.Pos.System.Payload.Response.ApiResponse;
import com.Aman.Aman.Pos.System.Payload.dto.InventoryDto;
import com.Aman.Aman.Pos.System.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventories")
@RequiredArgsConstructor
public class InventoryController {

    private final InventoryService inventoryService;

    // ✅ Create new inventory record
    @PostMapping
    public ResponseEntity<InventoryDto> createInventory(@RequestBody InventoryDto inventoryDto) {
        InventoryDto createdInventory = inventoryService.createInventory(inventoryDto);
        return ResponseEntity.ok(createdInventory);
    }

    // ✅ Update existing inventory
    @PutMapping("/{id}")
    public ResponseEntity<InventoryDto> updateInventory(@PathVariable Long id, @RequestBody InventoryDto inventoryDto) {
        InventoryDto updatedInventory = inventoryService.updateInventory(id, inventoryDto);
        return ResponseEntity.ok(updatedInventory);
    }

    // ✅ Get inventory by ID
    @GetMapping("/{id}")
    public ResponseEntity<InventoryDto> getInventoryById(@PathVariable Long id) {
        InventoryDto inventory = inventoryService.getInventoryById(id);
        return ResponseEntity.ok(inventory);
    }

    // ✅ Get inventory by productId and branchId
    @GetMapping("/by-product-and-branch")
    public ResponseEntity<InventoryDto> getInventoryByProductAndBranch(
            @RequestParam Long productId,
            @RequestParam Long branchId) {
        InventoryDto inventory = inventoryService.getInventoryByProductIdandBranchId(productId, branchId);
        return ResponseEntity.ok(inventory);
    }

    // ✅ Get all inventories for a branch
    @GetMapping("/branch/{branchId}")
    public ResponseEntity<List<InventoryDto>> getAllInventoriesByBranchId(@PathVariable Long branchId) {
        List<InventoryDto> inventories = inventoryService.getAllInventoriesByBranchId(branchId);
        return ResponseEntity.ok(inventories);
    }

    // ✅ Delete inventory by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteInventory(@PathVariable Long id) {
        inventoryService.deleteInventory(id);
        ApiResponse apiResponse=new ApiResponse();
        apiResponse.setMessage("Inventory deleted Successfully");
        return ResponseEntity.ok(apiResponse);
    }
}
