package com.Aman.Aman.Pos.System.Payload.dto;

import com.Aman.Aman.Pos.System.model.Store;
import jakarta.persistence.ManyToOne;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CategoryDto {
    private Long id;
    private String name;
    private Long storeId;
}
