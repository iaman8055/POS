package com.Aman.Aman.Pos.System.Repository;

import com.Aman.Aman.Pos.System.model.Store;
import org.springframework.data.jpa.repository.JpaRepository;

import com.Aman.Aman.Pos.System.model.User;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
    List<User> findByStore(Store store);
    List<User> findByBranchId(Long branchId);
}
