package com.Aman.Aman.Pos.System.Mapper;

import com.Aman.Aman.Pos.System.Payload.dto.userDto;
import com.Aman.Aman.Pos.System.model.User;

public class UserMapper {

    public static userDto toDto(User savedUser) {

        userDto user=new userDto();
        user.setId(savedUser.getId());
        user.setEmail(savedUser.getEmail());
        user.setUsername(savedUser.getUsername());
        user.setFullName(savedUser.getFullName());
        user.setRole(savedUser.getRole());
        user.setCreatedAt(savedUser.getCreatedAt());
        user.setUpdatedAt(savedUser.getUpdatedAt());
        user.setLastLogin(savedUser.getLastLogin());
        user.setPhone(savedUser.getPhone());
        return user;
         
    }

}
