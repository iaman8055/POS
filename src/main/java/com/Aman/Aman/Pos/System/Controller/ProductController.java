package com.Aman.Aman.Pos.System.Controller;

import com.Aman.Aman.Pos.System.Payload.Response.ApiResponse;
import com.Aman.Aman.Pos.System.Payload.dto.ProductDto;
import com.Aman.Aman.Pos.System.model.Product;
import com.Aman.Aman.Pos.System.model.User;
import com.Aman.Aman.Pos.System.service.ProductService;
import com.Aman.Aman.Pos.System.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/product")
public class ProductController {
    private final UserService userService;
    private final ProductService productService;
    @PostMapping
    public ResponseEntity<ProductDto> create(@RequestBody ProductDto productDto,@RequestHeader("Authorization") String jwt) throws Exception {
        User user=userService.getUserByToken(jwt);
        return ResponseEntity.ok(productService.createProduct(productDto,user));
    }
    @GetMapping("/store/{storeId}")
    public ResponseEntity<List<ProductDto>>getProductByStoreID(@PathVariable Long storeId, @RequestHeader("Authorization") String jwt){
       return ResponseEntity.ok(productService.getProdctsByStoreId(storeId));
    }
    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> getProductById(@PathVariable Long id,@RequestHeader("Authorization") String jwt) throws Exception {
        return ResponseEntity.ok(productService.getProductById(id));
    }
    @PutMapping("/{id}")
    public ResponseEntity<ProductDto>update(@PathVariable Long id,@RequestBody ProductDto productDto,@RequestHeader("Authorization") String jwt) throws Exception {
        User user=userService.getUserByToken(jwt);

        return ResponseEntity.ok(productService.updateProduct(id,productDto,user));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> delete(@RequestHeader("Authorization") String jwt,@PathVariable Long id) throws Exception {
        User user=userService.getUserByToken(jwt);

        productService.deleteProduct(id,user);
        ApiResponse apiResponse=new ApiResponse();
        apiResponse.setMessage("Product delete successfully");
       return ResponseEntity.ok(apiResponse);
    }
}
