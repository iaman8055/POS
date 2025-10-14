package com.Aman.Aman.Pos.System.service.impl;

import com.Aman.Aman.Pos.System.Mapper.CategoryMapper;
import com.Aman.Aman.Pos.System.Payload.dto.CategoryDto;
import com.Aman.Aman.Pos.System.Repository.CategoryRepository;
import com.Aman.Aman.Pos.System.Repository.StoreRepository;
import com.Aman.Aman.Pos.System.domain.UserRole;
import com.Aman.Aman.Pos.System.model.Category;
import com.Aman.Aman.Pos.System.model.Store;
import com.Aman.Aman.Pos.System.model.User;
import com.Aman.Aman.Pos.System.service.CategoryService;
import com.Aman.Aman.Pos.System.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final UserService userService;
    private final StoreRepository storeRepository;
    @Override
    public CategoryDto createCategory(CategoryDto dto) throws Exception {
        User user=userService.getCurrentUser();
        Store store= storeRepository.findById(dto.getStoreId()).orElseThrow(()->
                new Exception("Store not found"));
        checkAuthority(user,store);
        Category category=Category.builder()
                .id(dto.getId())
                .name(dto.getName())
                .store(store).build();


        return CategoryMapper.toDto(categoryRepository.save(category));
    }

    @Override
    public List<CategoryDto> getCategoriesByStore(Long storeId) {
        List<Category> categories=categoryRepository.findByStoreId(storeId);
        return categories.stream().map(CategoryMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public CategoryDto updateCategory(Long id, CategoryDto categoryDto) throws Exception {
        Category category=categoryRepository.findById(id).orElseThrow(()->new Exception("Category not found"));
        User user=userService.getCurrentUser();
        checkAuthority(user,category.getStore());
        category.setName(categoryDto.getName());
        return CategoryMapper.toDto(categoryRepository.save(category));
    }

    @Override
    public void deleteCategory(Long id) throws Exception {
        Category category=categoryRepository.findById(id).orElseThrow(()->new Exception("Category not found"));
        User user=userService.getCurrentUser();
        checkAuthority(user,category.getStore());
        categoryRepository.delete(category);
    }
    private void checkAuthority(User user,Store store) throws Exception {
        boolean isAdmin=user.getRole().equals(UserRole.ROLE_ADMIN);
        boolean isManager=user.getRole().equals(UserRole.ROLE_STORE_MANAGER);
        boolean isSameStore=user.equals(store.getStoreAdmin());

        if(!(isAdmin && isSameStore) && !isManager){
            throw new Exception("You don't have the permission to manage this category");
        }
    }
}
