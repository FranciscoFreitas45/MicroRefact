package com.example.smartkitchenbackend.DTOs.authentication;
 import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotBlank;
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class LoginRequest {

@NotBlank
 private  String usernameOrEmail;

@NotBlank
 private  String password;


}