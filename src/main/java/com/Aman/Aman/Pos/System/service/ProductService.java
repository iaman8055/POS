package com.Aman.Aman.Pos.System.service;

import com.Aman.Aman.Pos.System.Payload.dto.ProductDto;
import com.Aman.Aman.Pos.System.model.User;

import java.util.List;

public interface ProductService {
     ProductDto createProduct(ProductDto productDto, User user) throws Exception;
     ProductDto updateProduct(Long id, ProductDto productDto, User user) throws Exception;
     void deleteProduct(Long id, User user) throws Exception;
     ProductDto getProductById(Long id) throws Exception;
     List<ProductDto>getProdctsByStoreId(Long storeId);
     List<ProductDto>getProductsByKeyword(Long storeId,String Keyword);
}
