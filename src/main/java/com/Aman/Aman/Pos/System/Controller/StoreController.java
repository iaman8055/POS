package com.Aman.Aman.Pos.System.Controller;


import com.Aman.Aman.Pos.System.Payload.Response.ApiResponse;
import com.Aman.Aman.Pos.System.Payload.dto.StoreDto;
import com.Aman.Aman.Pos.System.model.Store;
import com.Aman.Aman.Pos.System.model.User;
import com.Aman.Aman.Pos.System.service.StoreService;
import com.Aman.Aman.Pos.System.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/store")
public class StoreController {
    private final StoreService storeService;
    private final UserService userService;
    @PostMapping
    public ResponseEntity<StoreDto> createStore(@RequestBody StoreDto storeDto, @RequestHeader("Authorization") String jwt){
        User user=userService.getUserByToken(jwt);
        return ResponseEntity.ok(storeService.createStore(storeDto,user));
    }
    @GetMapping("/{id}")
    public  ResponseEntity<StoreDto>getStoreById(@RequestHeader("Authorization") String jwt,@PathVariable Long id) throws Exception {
        return ResponseEntity.ok(storeService.getStoreById(id));
    }
    @GetMapping
    public ResponseEntity<List<StoreDto>> getAllStore(){
        return ResponseEntity.ok(storeService.getAllStores());
    }
    @GetMapping("/admin")
    public ResponseEntity<Store> getStoreByAdmin(@RequestHeader("Authorization") String jwt){
        return ResponseEntity.ok(storeService.getStoreByAdmin());
    }
    @GetMapping("/employee")
    public ResponseEntity<StoreDto> getStoreByEmployee(@RequestHeader("Authorization") String jwt) throws Exception {
        return ResponseEntity.ok(storeService.getStoreByEmployee());
    }
    @PutMapping("/{id}")
    public ResponseEntity<StoreDto>updateStore(@RequestBody StoreDto storeDto,@PathVariable Long id) throws Exception {
        return ResponseEntity.ok(storeService.updateStore(id,storeDto));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteStore(@RequestHeader("Authorization") String jwt,@PathVariable Long id){
        storeService.deleteStore(id);
        ApiResponse apiResponse=new ApiResponse();
        apiResponse.setMessage("Store delete successfully");
        return ResponseEntity.ok(apiResponse);
    }
}
