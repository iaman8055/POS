package com.Aman.Aman.Pos.System.service;

import com.Aman.Aman.Pos.System.Exceptions.UserException;
import com.Aman.Aman.Pos.System.Payload.Response.AuthResponse;
import com.Aman.Aman.Pos.System.Payload.dto.userDto;

public interface AuthService  {
    AuthResponse signup(userDto userDto) throws UserException;
    AuthResponse login(userDto userDto) throws UserException;
}
