package com.Aman.Aman.Pos.System.Controller;

import com.Aman.Aman.Pos.System.Payload.Response.ApiResponse;
import com.Aman.Aman.Pos.System.Payload.dto.userDto;
import com.Aman.Aman.Pos.System.domain.UserRole;
import com.Aman.Aman.Pos.System.model.User;
import com.Aman.Aman.Pos.System.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    // ✅ Create employee under a store
    @PostMapping("/store/{storeId}")
    public ResponseEntity<userDto> createStoreEmployee(
            @PathVariable Long storeId,
            @RequestBody userDto employeeDto) throws Exception {

        userDto created = employeeService.createStoreEmployee(employeeDto, storeId);
        return ResponseEntity.ok(created);
    }

    // ✅ Create employee under a branch
    @PostMapping("/branch/{branchId}")
    public ResponseEntity<userDto> createBranchEmployee(
            @PathVariable Long branchId,
            @RequestBody userDto employeeDto) throws Exception {

        userDto created = employeeService.createBranchEmployee(employeeDto, branchId);
        return ResponseEntity.ok(created);
    }

    // ✅ Update employee details
    @PutMapping("/{id}")
    public ResponseEntity<User> updateEmployee(
            @PathVariable Long id,
            @RequestBody userDto employeeDetails) throws Exception {

        User updated = employeeService.updateEmployee(id, employeeDetails);
        return ResponseEntity.ok(updated);
    }

    // ✅ Delete employee
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteEmployee(@PathVariable Long id) throws Exception {
        employeeService.deleteEmployee(id);
        ApiResponse apiResponse=new ApiResponse();
        apiResponse.setMessage("Employee deleted successfully");
        return ResponseEntity.ok(apiResponse);
    }

    // ✅ Get all employees in a store (optionally filtered by role)
    @GetMapping("/store/{storeId}")
    public ResponseEntity<List<User>> getStoreEmployees(
            @PathVariable Long storeId,
            @RequestParam(required = false) UserRole role) throws Exception {

        List<User> employees = employeeService.findStoreEmployee(storeId, role);
        return ResponseEntity.ok(employees);
    }

    // ✅ Get all employees in a branch (optionally filtered by role)
    @GetMapping("/branch/{branchId}")
    public ResponseEntity<List<User>> getBranchEmployees(
            @PathVariable Long branchId,
            @RequestParam(required = false) UserRole role) throws Exception {

        List<User> employees = employeeService.findBranchEmployee(branchId, role);
        return ResponseEntity.ok(employees);
    }
}
