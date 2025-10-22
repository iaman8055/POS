package com.Aman.Aman.Pos.System.Payload.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Builder
@Data
public class InventoryDto {

    private Long id;
    private Long branchId;
    private BranchDto branch;
    private Long productId;
    private ProductDto product;
    private Integer quantity;
    private LocalDateTime lastUpdated;

}
