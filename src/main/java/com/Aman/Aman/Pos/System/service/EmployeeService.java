package com.Aman.Aman.Pos.System.service;

import com.Aman.Aman.Pos.System.Payload.dto.userDto;
import com.Aman.Aman.Pos.System.domain.UserRole;
import com.Aman.Aman.Pos.System.model.User;

import java.util.List;

public interface EmployeeService {
    userDto createStoreEmployee(userDto employee,Long storeId) throws Exception;
    userDto createBranchEmployee(userDto employee,Long branchId) throws Exception;
    User updateEmployee(Long employeeId,userDto employeeDetails) throws Exception;
    void deleteEmployee(Long employeeId) throws Exception;
    List<User> findStoreEmployee(Long storeId, UserRole role) throws Exception;
    List<User> findBranchEmployee(Long branchId,UserRole role) throws Exception;
}
