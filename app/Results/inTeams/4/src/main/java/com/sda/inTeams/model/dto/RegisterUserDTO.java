package com.sda.inTeams.model.dto;
 import lombok.Data;
@Data
public class RegisterUserDTO implements RegisterDto{

 private  long teamId;

 private  String registerCode;

 private  String username;

 private  String password;

 private  String confirmPassword;

 private  String firstName;

 private  String lastName;


}