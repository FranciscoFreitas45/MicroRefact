package com.example.smartkitchenbackend.DTOs;
 import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class UserDTO {

 private  long id;

 private  String name;

 private  String email;

 private  String userName;


}