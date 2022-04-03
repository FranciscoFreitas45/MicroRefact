package com.puffride.demo.rest;
 import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalTime;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthObj {

 private  String email;

 private  String password;


}