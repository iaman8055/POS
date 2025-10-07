package com.Aman.Aman.Pos.System.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Aman.Aman.Pos.System.Exceptions.UserException;
import com.Aman.Aman.Pos.System.Payload.Response.AuthResponse;
import com.Aman.Aman.Pos.System.Payload.dto.userDto;
import com.Aman.Aman.Pos.System.service.AuthService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<AuthResponse> signupHandler(@RequestBody userDto userDto) throws UserException{
        return ResponseEntity.ok(authService.signup(userDto));
    }
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> loginHandler(@RequestBody userDto userDto) throws UserException{
        return ResponseEntity.ok(authService.login(userDto));
    }
    
}
