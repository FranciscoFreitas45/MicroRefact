package com.app.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class LoginController {

 private Login login;

 private Login login;


@PutMapping
("/setEnableInstitute")
public void setEnableInstitute(@RequestParam(name = "enableInstitute") boolean enableInstitute){
login.setEnableInstitute(enableInstitute);
}


@PutMapping
("/setPassword")
public void setPassword(@RequestParam(name = "password") String password){
login.setPassword(password);
}


@PutMapping
("/setUsername")
public void setUsername(@RequestParam(name = "username") String username){
login.setUsername(username);
}


}