package com.Aman.Aman.Pos.System.Mapper;

import java.time.LocalDateTime;

import com.Aman.Aman.Pos.System.Payload.dto.ProductDto;
import com.Aman.Aman.Pos.System.model.Category;
import com.Aman.Aman.Pos.System.model.Product;
import com.Aman.Aman.Pos.System.model.Store;

public class ProductMapper {

    public static ProductDto toDto(Product product){
        return ProductDto.builder()
                .id(product.getId())
                .name(product.getName())
                .sku(product.getSku())
                .description(product.getDescription())
                .mrp(product.getMrp())
                .sellingPrice(product.getSellingPrice())
                .brand(product.getBrand())
                .category(CategoryMapper.toDto(product.getCategory()))
                .storeId(product.getStore()!=null?product.getStore().getId():null)
                .image(product.getImage())
                .createdAt(product.getCreatedAt())
                .updatedAt(product.getUpdatedAt())
                .build();
    }
    public static Product toEntity(ProductDto productDto, Store store, Category category){
        return Product.builder()
                .id(productDto.getId())
                .name(productDto.getName())
                .sku(productDto.getSku())
                .description(productDto.getDescription())
                .store(store)
                .category(category)
                .mrp(productDto.getMrp())
                .sellingPrice(productDto.getSellingPrice())
                .brand(productDto.getBrand())
                .createdAt(LocalDateTime.now())
                .build();
    }
}
