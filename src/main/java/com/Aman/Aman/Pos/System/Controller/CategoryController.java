package com.Aman.Aman.Pos.System.Controller;

import com.Aman.Aman.Pos.System.Payload.Response.ApiResponse;
import com.Aman.Aman.Pos.System.Payload.dto.CategoryDto;
import com.Aman.Aman.Pos.System.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/category")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    @PostMapping
    public ResponseEntity<CategoryDto> create(@RequestBody CategoryDto categoryDto,@RequestHeader("Authorization") String jwt) throws Exception {
       return ResponseEntity.ok(categoryService.createCategory(categoryDto));
    }

    @GetMapping("/store/{storeId}")
    public ResponseEntity<List<CategoryDto>> getCategoryByStore(@RequestHeader("Authorization") String jwt,@PathVariable Long storeId){
        return ResponseEntity.ok(categoryService.getCategoriesByStore(storeId));
    }
    @PutMapping("/{id}")
    public ResponseEntity<CategoryDto>update(@PathVariable Long id,@RequestBody CategoryDto categoryDto) throws Exception {
        return ResponseEntity.ok(categoryService.updateCategory(id,categoryDto));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse>delete(@PathVariable Long id) throws Exception {
        categoryService.deleteCategory(id);
        ApiResponse apiResponse=new ApiResponse();
        apiResponse.setMessage("category delete succcessfully");
        return ResponseEntity.ok(apiResponse);
    }
}
