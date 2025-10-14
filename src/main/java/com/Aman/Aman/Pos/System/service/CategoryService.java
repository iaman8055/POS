package com.Aman.Aman.Pos.System.service;

import com.Aman.Aman.Pos.System.Payload.dto.CategoryDto;

import java.util.List;

public interface CategoryService {
    CategoryDto createCategory(CategoryDto dto) throws Exception;
    List<CategoryDto> getCategoriesByStore(Long storeId);
    CategoryDto updateCategory(Long id,CategoryDto categoryDto) throws Exception;
    void deleteCategory(Long id) throws Exception;

}
