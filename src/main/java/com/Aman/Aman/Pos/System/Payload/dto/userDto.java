package com.Aman.Aman.Pos.System.Payload.dto;

import java.time.LocalDate;

import com.Aman.Aman.Pos.System.domain.UserRole;

import lombok.Data;
import lombok.Getter;


@Data
public class userDto {
    private Long id;
    private String fullName;
    private String username;
    private String email;
    private String phone;
    private String password;
    private UserRole role;
    private Long storeId;
    private Long branchId;
    private LocalDate createdAt;
    private LocalDate updatedAt;
    private LocalDate lastLogin;
}
