package com.Aman.Aman.Pos.System.Payload.Response;

import com.Aman.Aman.Pos.System.Payload.dto.userDto;

import lombok.Data;
@Data
public class AuthResponse {

    private String jwt;
    private String message;
    private userDto user;

}
