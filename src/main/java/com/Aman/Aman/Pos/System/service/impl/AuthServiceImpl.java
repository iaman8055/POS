package com.Aman.Aman.Pos.System.service.impl;

import java.time.LocalDate;
import java.util.Collection;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.Aman.Aman.Pos.System.Exceptions.UserException;
import com.Aman.Aman.Pos.System.Mapper.UserMapper;
import com.Aman.Aman.Pos.System.Payload.Response.AuthResponse;
import com.Aman.Aman.Pos.System.Payload.dto.userDto;
import com.Aman.Aman.Pos.System.Repository.UserRepository;
import com.Aman.Aman.Pos.System.configuration.JwtProvider;
import com.Aman.Aman.Pos.System.domain.UserRole;
import com.Aman.Aman.Pos.System.model.User;
import com.Aman.Aman.Pos.System.service.AuthService;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final JwtProvider jwtProvider;
    private final PasswordEncoder passwordEncoder;
    private final CustomUserImplementation customUserImplementation;

    @Override
    public AuthResponse signup(userDto userDto) throws UserException {
        System.out.println("user data:"+userDto);

        User user=userRepository.findByEmail(userDto.getEmail());

        if(user!=null){
            throw new UserException("User with this email already registered");

        }
        if(userDto.getRole().equals(UserRole.ROLE_ADMIN)){
            throw new UserException("role admin is not allowed");
        }
        User newUser=new User();
        newUser.setUsername(userDto.getUsername());
        newUser.setFullName(userDto.getFullName());
        newUser.setEmail(userDto.getEmail());
        newUser.setPassword(passwordEncoder.encode(userDto.getPassword()));
        newUser.setRole(userDto.getRole());
        newUser.setPhone(userDto.getPhone());
        newUser.setCreatedAt(LocalDate.now());
        newUser.setUpdatedAt(LocalDate.now());
        newUser.setLastLogin(LocalDate.now());

        User savedUser=userRepository.save(newUser);

        Authentication authentication=new UsernamePasswordAuthenticationToken(userDto.getEmail(), userDto.getPassword());
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt=jwtProvider.generateToken(authentication);
        AuthResponse authResponse=new AuthResponse();
        authResponse.setJwt(jwt);
        authResponse.setMessage("Registered Successfully");
        authResponse.setUser(UserMapper.toDto(savedUser));
        return authResponse;
    }
    @Override
    public AuthResponse login(userDto userDto) throws UserException{
        String email=userDto.getEmail();
        String password=userDto.getPassword();
        Authentication authentication=authenticate(email,password);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        Collection<? extends GrantedAuthority>authorities=authentication.getAuthorities();
        String role=authorities.iterator().next().getAuthority();
        String jwt=jwtProvider.generateToken(authentication);
        User user =userRepository.findByEmail(email);
        user.setLastLogin(LocalDate.now());
        userRepository.save(user);
        AuthResponse authResponse=new AuthResponse();
        authResponse.setJwt(jwt);
        authResponse.setMessage("Login Successfully");
        authResponse.setUser(UserMapper.toDto(user));
        return authResponse;    
    }

    private Authentication authenticate(String email, String password) throws UserException {

        UserDetails userDetails=customUserImplementation.loadUserByUsername(email);
        if(userDetails==null){
            throw new UserException("email id doesn't exist");
        }

        if(!passwordEncoder.matches(password, userDetails.getPassword())){
            throw new UserException("password does not match");
        }

        return new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
    }
}
