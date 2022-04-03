package com.sda.inTeams.model.dto;
 import lombok.Data;
@Data
public class RegisterTeamDTO implements RegisterDto{

 private  String teamName;

 private  String username;

 private  String password;

 private  String confirmPassword;

 private  String firstName;

 private  String lastName;


}