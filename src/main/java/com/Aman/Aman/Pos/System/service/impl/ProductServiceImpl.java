package com.Aman.Aman.Pos.System.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import com.Aman.Aman.Pos.System.Repository.CategoryRepository;
import com.Aman.Aman.Pos.System.model.Category;
import org.springframework.stereotype.Service;

import com.Aman.Aman.Pos.System.Mapper.ProductMapper;
import com.Aman.Aman.Pos.System.Payload.dto.ProductDto;
import com.Aman.Aman.Pos.System.Repository.ProductRepository;
import com.Aman.Aman.Pos.System.Repository.StoreRepository;
import com.Aman.Aman.Pos.System.model.Product;
import com.Aman.Aman.Pos.System.model.Store;
import com.Aman.Aman.Pos.System.model.User;
import com.Aman.Aman.Pos.System.service.ProductService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final StoreRepository storeRepository;
    private final CategoryRepository categoryRepository;
    @Override
    public ProductDto createProduct(ProductDto productDto, User user) throws Exception {
        Store store=storeRepository.findById(productDto.getStoreId()).orElseThrow(
                ()->new Exception("Store not found")
        );
        Category category=categoryRepository.findById(productDto.getCategoryId()).orElseThrow(
                ()->new Exception("Category not found")
        );
        Product product= ProductMapper.toEntity(productDto,store,category);
       Product savedProduct= productRepository.save(product);
        return ProductMapper.toDto(savedProduct);
    }

    @Override
    public ProductDto updateProduct(Long id, ProductDto productDto, User user) throws Exception {
        Product product=productRepository.findById(id).orElseThrow(()->
                new Exception("Product not found"));

        product.setName(productDto.getName());
        product.setDescription(productDto.getDescription());
        product.setSku(productDto.getSku());
        product.setBrand(productDto.getBrand());
        product.setImage(productDto.getImage());
        product.setMrp(productDto.getMrp());
        product.setSellingPrice(productDto.getSellingPrice());
        product.setUpdatedAt(LocalDateTime.now());
        if(productDto.getCategoryId()!=null){
            Category category=categoryRepository.findById(productDto.getCategoryId()).orElseThrow(
                    ()->new Exception("Category not found")
            );
            product.setCategory(category);
        }

        Product updatedProdct=productRepository.save(product);

        return ProductMapper.toDto(updatedProdct);
    }

    @Override
    public void deleteProduct(Long id, User user) throws Exception {
        Product product=productRepository.findById(id).orElseThrow(()->
                new Exception("Product not found"));
        productRepository.delete(product);
    }

    @Override
    public ProductDto getProductById(Long id) throws Exception {
        Product product=productRepository.findById(id).orElseThrow(()->
                new Exception("Product not found"));

        return ProductMapper.toDto(product);
    }

    @Override
    public List<ProductDto> getProdctsByStoreId(Long storeId) {
        List<Product>products=productRepository.findByStoreId(storeId);
        return products.stream().map(ProductMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public List<ProductDto> getProductsByKeyword(Long storeId, String Keyword) {
        List<Product>products=productRepository.findByKeyword(storeId,Keyword);
        return products.stream().map(ProductMapper::toDto).collect(Collectors.toList());
    }
}
