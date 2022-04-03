package com.bau.graduateprojects.qrstudentsattendance.controllers;
 import com.bau.graduateprojects.qrstudentsattendance.servicies.secret.SecretService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("/api/v1/secret")
public class SecretController {

 private  SecretService secretService;

public SecretController(SecretService secretService) {
    this.secretService = secretService;
}
@GetMapping("/{cId}")
public String getKeyByCourseId(Long cId){
    return secretService.getKey(cId);
}


}