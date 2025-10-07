package com.Aman.Aman.Pos.System.service;

import java.util.List;

import com.Aman.Aman.Pos.System.model.User;



public interface UserService {
    User getUserByToken(String token);
    User getUserById(Long id);
    User getUserByEmail(String email);
    User getCurrentUser();
    List<User>getAllUsers();
}
