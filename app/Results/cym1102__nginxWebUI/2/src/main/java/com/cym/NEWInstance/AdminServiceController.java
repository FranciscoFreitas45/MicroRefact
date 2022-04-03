package com.cym.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class AdminServiceController {

 private AdminService adminservice;


@GetMapping
("/getByToken")
public Admin getByToken(@RequestParam(name = "token") String token){
  return adminservice.getByToken(token);
}


@GetMapping
("/getByCreditKey")
public Admin getByCreditKey(@RequestParam(name = "creditKey") String creditKey){
  return adminservice.getByCreditKey(creditKey);
}


@GetMapping
("/getGroupIds")
public List<String> getGroupIds(@RequestParam(name = "adminId") String adminId){
  return adminservice.getGroupIds(adminId);
}


}