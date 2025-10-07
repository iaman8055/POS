package com.Aman.Aman.Pos.System.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Aman.Aman.Pos.System.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}
