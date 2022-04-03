package com.example.smartkitchenbackend.DTOs.authentication;
 import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class SignUpRequest {

@NotBlank
@Size(min = 4, max = 40)
 private  String name;

@NotBlank
@Size(min = 3, max = 15)
 private  String username;

@NotBlank
@Size(max = 40)
@Email
 private  String email;

@NotBlank
@Size(min = 6, max = 20)
 private  String password;


}