package com.Aman.Aman.Pos.System.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Aman.Aman.Pos.System.Exceptions.UserException;
import com.Aman.Aman.Pos.System.Mapper.UserMapper;
import com.Aman.Aman.Pos.System.Payload.dto.userDto;
import com.Aman.Aman.Pos.System.model.User;
import com.Aman.Aman.Pos.System.service.UserService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/user")
public class UserController {
    private final UserService userService;
    @GetMapping("/profile")
    public ResponseEntity<userDto>getUserProfile(@RequestHeader("Authorization") String jwt){
        User user= userService.getUserByToken(jwt);
        return ResponseEntity.ok(UserMapper.toDto(user));
    } 
    @GetMapping("/{id}")
    public ResponseEntity<userDto>getUserById(@RequestHeader("Authorization") String jwt, @PathVariable Long id) throws UserException{
        User user=userService.getUserById(id);
        if(user==null){
            throw new UserException("User not found");
        }
        return ResponseEntity.ok(UserMapper.toDto(user));
    }
    @GetMapping("/{email}")
    public ResponseEntity<userDto>getUserByEmail(@RequestHeader("Authorization")String jwt, @PathVariable String email) throws UserException{
        User user=userService.getUserByEmail(email);
        if(user==null){
            throw new UserException("User not found");
        }
        return ResponseEntity.ok(UserMapper.toDto(user));
    }

}
