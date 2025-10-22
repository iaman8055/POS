package com.Aman.Aman.Pos.System.Controller;

import com.Aman.Aman.Pos.System.Payload.Response.ApiResponse;
import com.Aman.Aman.Pos.System.Payload.dto.BranchDto;
import com.Aman.Aman.Pos.System.model.User;
import com.Aman.Aman.Pos.System.service.BranchService;
import com.Aman.Aman.Pos.System.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/branches")
@RequiredArgsConstructor
public class BranchController {

    private final BranchService branchService;
    private final UserService userService;

    // ✅ Create a new Branch
    @PostMapping
    public ResponseEntity<BranchDto> createBranch(@RequestBody BranchDto branchDto) throws Exception {
        User currentUser = userService.getCurrentUser();
        BranchDto createdBranch = branchService.createBranch(branchDto, currentUser);
        return ResponseEntity.ok(createdBranch);
    }

    // ✅ Update a Branch
    @PutMapping("/{id}")
    public ResponseEntity<BranchDto> updateBranch(
            @PathVariable Long id,
            @RequestBody BranchDto branchDto) throws Exception {

        User currentUser = userService.getCurrentUser();
        BranchDto updatedBranch = branchService.updateBranch(id, branchDto, currentUser);
        return ResponseEntity.ok(updatedBranch);
    }

    // ✅ Delete a Branch
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteBranch(@PathVariable Long id) throws Exception {
        branchService.deleteBranch(id);
        ApiResponse apiResponse=new ApiResponse();
        apiResponse.setMessage("Branch deleted successfully.");
        return ResponseEntity.ok(apiResponse);
    }

    // ✅ Get All Branches of a Store
    @GetMapping("/store/{storeId}")
    public ResponseEntity<List<BranchDto>> getBranchesByStoreId(@PathVariable Long storeId) throws Exception {
        List<BranchDto> branches = branchService.getAllBranchesbyStoreId(storeId);
        return ResponseEntity.ok(branches);
    }

    // ✅ Get Branch by ID
    @GetMapping("/{id}")
    public ResponseEntity<BranchDto> getBranchById(@PathVariable Long id) throws Exception {
        BranchDto branch = branchService.getBranchById(id);
        return ResponseEntity.ok(branch);
    }
}
