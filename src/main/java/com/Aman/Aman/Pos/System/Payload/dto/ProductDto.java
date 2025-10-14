package com.Aman.Aman.Pos.System.Payload.dto;

import com.Aman.Aman.Pos.System.model.Store;
import jakarta.persistence.Column;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ProductDto {
    private Long id;
    private String name;
    private String sku;
    private String description;
    private double mrp;
    private double sellingPrice;
    private String brand;
    private String image;
    private CategoryDto category;
    private Long categoryId;
    private Long storeId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
