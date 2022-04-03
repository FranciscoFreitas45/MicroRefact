package org.gliderwiki.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class LoginServiceController {

 private LoginService loginservice;


@GetMapping
("/getEncryptPassword")
public String getEncryptPassword(@RequestParam(name = "passKey") String passKey,@RequestParam(name = "passVal") String passVal){
  return loginservice.getEncryptPassword(passKey,passVal);
}


}