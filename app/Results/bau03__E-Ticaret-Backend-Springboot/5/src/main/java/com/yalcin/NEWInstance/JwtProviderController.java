package com.yalcin.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class JwtProviderController {

 private JwtProvider jwtprovider;


@GetMapping
("/getSubjectFromJwt")
public String getSubjectFromJwt(@RequestParam(name = "token") String token,@RequestParam(name = "matter") String matter){
  return jwtprovider.getSubjectFromJwt(token,matter);
}


@GetMapping
("/validateJwtToken")
public boolean validateJwtToken(@RequestParam(name = "authToken") String authToken,@RequestParam(name = "matter") String matter,@RequestParam(name = "request") HttpServletRequest request){
  return jwtprovider.validateJwtToken(authToken,matter,request);
}


@GetMapping
("/generateJwtTokenForVerification")
public String generateJwtTokenForVerification(@RequestParam(name = "user") User user){
  return jwtprovider.generateJwtTokenForVerification(user);
}


@GetMapping
("/generateJwtTokenForPassword")
public String generateJwtTokenForPassword(@RequestParam(name = "email") String email){
  return jwtprovider.generateJwtTokenForPassword(email);
}


}