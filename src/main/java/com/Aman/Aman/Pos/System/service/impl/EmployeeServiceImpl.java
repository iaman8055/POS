package com.Aman.Aman.Pos.System.service.impl;

import com.Aman.Aman.Pos.System.Mapper.UserMapper;
import com.Aman.Aman.Pos.System.Payload.dto.userDto;
import com.Aman.Aman.Pos.System.Repository.BranchRepository;
import com.Aman.Aman.Pos.System.Repository.StoreRepository;
import com.Aman.Aman.Pos.System.Repository.UserRepository;
import com.Aman.Aman.Pos.System.domain.UserRole;
import com.Aman.Aman.Pos.System.model.Branch;
import com.Aman.Aman.Pos.System.model.Store;
import com.Aman.Aman.Pos.System.model.User;
import com.Aman.Aman.Pos.System.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
    private final UserRepository userRepository;
    private final StoreRepository storeRepository;
    private final BranchRepository branchRepository;
    private final PasswordEncoder passwordEncoder;
    @Override
    public userDto createStoreEmployee(userDto employee, Long storeId) throws Exception {
        Store store=storeRepository.findById(storeId).orElseThrow(()-> new Exception("store not found"));
        Branch branch=null;

        if(employee.getRole()==UserRole.ROLE_BRANCH_MANAGER){
            if(employee.getBranchId()==null){
                throw  new Exception("Branch id is reqired to create branch manager");
            }
            branch=branchRepository.findById(employee.getBranchId()).orElseThrow(()-> new Exception("Branch is not found"));
        }
        User user= UserMapper.toEntity(employee);
        user.setBranch(branch);
        user.setStore(store);
        user.setPassword(passwordEncoder.encode(employee.getPassword()));
        User savedEmployee=userRepository.save(user);

        if(employee.getRole()==UserRole.ROLE_BRANCH_MANAGER && branch!=null){
            branch.setManager(savedEmployee);
            branchRepository.save(branch);
        }
        return UserMapper.toDto(savedEmployee);
    }

    @Override
    public userDto createBranchEmployee(userDto employee, Long branchId) throws Exception {
        Branch branch = branchRepository.findById(branchId).orElseThrow(() -> new Exception("Branch not found"));
        if (employee.getRole() != UserRole.ROLE_BRANCH_CASHIER || employee.getRole() != UserRole.ROLE_BRANCH_MANAGER) {
            User user = UserMapper.toEntity(employee);
            user.setBranch(branch);
            user.setPassword(passwordEncoder.encode(employee.getPassword()));
            return UserMapper.toDto(userRepository.save(user));
        }
        throw new Exception("Branch role is not supported");

    }

    @Override
    public User updateEmployee(Long employeeId, userDto employeeDetails) throws Exception {

        User existingUser=userRepository.findById(employeeId).orElseThrow(()->new Exception("User not found"));
        Branch branch=branchRepository.findById(employeeDetails.getBranchId()).orElseThrow(()->new Exception("Branch not found"));
        existingUser.setBranch(branch);
        existingUser.setEmail(employeeDetails.getEmail());
        existingUser.setFullName(employeeDetails.getFullName());
        existingUser.setPassword(employeeDetails.getPassword());
        existingUser.setRole(employeeDetails.getRole());
        return userRepository.save(existingUser);
    }

    @Override
    public void deleteEmployee(Long employeeId) throws Exception {

        User employee=userRepository.findById(employeeId).orElseThrow(()->new Exception("Employee not found"));
        userRepository.delete(employee);

    }

    @Override
    public List<User> findStoreEmployee(Long storeId, UserRole role) throws Exception {
        Store store=storeRepository.findById(storeId).orElseThrow(()-> new Exception("Store is not found"));

        return userRepository.findByStore(store).stream().filter(user -> role==null||user.getRole()==role).collect(Collectors.toList());
    }

    @Override
    public List<User> findBranchEmployee(Long branchId, UserRole role) throws Exception {
        Branch branch=branchRepository.findById(branchId).orElseThrow(()->new Exception("Branch not found"));
        List<User> employee=userRepository.findByBranchId(branchId).stream().filter(user -> role==null ||user.getRole()==role).collect(Collectors.toList())
        return employee;
    }
}
