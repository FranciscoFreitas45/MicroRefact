package com.webapp.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class JwtUtilsController {

 private JwtUtils jwtutils;


@GetMapping
("/validateJwtToken")
public boolean validateJwtToken(@RequestParam(name = "authToken") String authToken){
  return jwtutils.validateJwtToken(authToken);
}


@GetMapping
("/getUserNameFromJwtToken")
public String getUserNameFromJwtToken(@RequestParam(name = "token") String token){
  return jwtutils.getUserNameFromJwtToken(token);
}


}