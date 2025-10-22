package com.Aman.Aman.Pos.System.Mapper;


import com.Aman.Aman.Pos.System.Payload.dto.InventoryDto;
import com.Aman.Aman.Pos.System.model.Inventory;
import com.Aman.Aman.Pos.System.model.Branch;
import com.Aman.Aman.Pos.System.model.Product;

public class InventoryMapper {

    public static InventoryDto toDto(Inventory inventory) {
        if (inventory == null) {
            return null;
        }

        return InventoryDto.builder()
                .id(inventory.getId())
                .productId(inventory.getProduct().getId())
                .branchId(inventory.getBranch().getId())
                .product(ProductMapper.toDto(inventory.getProduct()))
                .quantity(inventory.getQuantity())
                .lastUpdated(inventory.getLastUpdated())
                .build();
    }

    public static Inventory toEntity(InventoryDto inventoryDto, Branch branch, Product product) {
        if (inventoryDto == null) {
            return null;
        }

        return Inventory.builder()
                .branch(branch)
                .product(product)
                .quantity(inventoryDto.getQuantity())
                .build();
    }
}
