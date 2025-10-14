package com.Aman.Aman.Pos.System.service.impl;

import java.util.List;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.Aman.Aman.Pos.System.Repository.UserRepository;
import com.Aman.Aman.Pos.System.configuration.JwtProvider;
import com.Aman.Aman.Pos.System.model.User;
import com.Aman.Aman.Pos.System.service.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;
    private final JwtProvider jwtProvider;
    @Override
    public User getUserByToken(String token) {
        String email=jwtProvider.getEmailfromToken(token);
        User user=userRepository.findByEmail(email);
        return user;
    }

    @Override
    public User getUserById(Long id) {
        User user=userRepository.findById(id).orElse(null);
        return user;
    }

    @Override
    public User getUserByEmail(String email) {
        User user=userRepository.findByEmail(email);
        return user;
    }

    @Override
    public User getCurrentUser() {
        String email=SecurityContextHolder.getContext().getAuthentication().getName();
        User user=userRepository.findByEmail(email);
        return user;
    }

    @Override
    public List<User> getAllUsers() {
        List<User>user=userRepository.findAll();
        return user;
    }

}
