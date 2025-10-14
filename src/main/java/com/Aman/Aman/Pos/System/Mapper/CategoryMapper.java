package com.Aman.Aman.Pos.System.Mapper;

import com.Aman.Aman.Pos.System.Payload.dto.CategoryDto;
import com.Aman.Aman.Pos.System.model.Category;

public class CategoryMapper {
    public static CategoryDto toDto(Category category){
        return CategoryDto.builder()
                .id(category.getId())
                .name(category.getName())
                .storeId(category.getStore()!=null?category.getStore().getId():null)
                .build();
    }
}
