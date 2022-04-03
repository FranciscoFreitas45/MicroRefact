package com.example.smartkitchenbackend.controllers;
 import com.example.smartkitchenbackend.DTOs.authentication.LoginRequest;
import com.example.smartkitchenbackend.DTOs.authentication.SignUpRequest;
import com.example.smartkitchenbackend.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

 private  UserService userService;


@PostMapping("/signin")
public ResponseEntity<?> authenticateUser(LoginRequest loginRequest){
    return userService.authenticateUser(loginRequest);
}


@PostMapping("/signup")
public ResponseEntity<?> registerUser(SignUpRequest signUpRequest){
    return userService.registerUser(signUpRequest);
}


}