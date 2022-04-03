package com.cym.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class PasswordController {

 private Password password;

 private Password password;


@PutMapping
("/setPathStr")
public void setPathStr(@RequestParam(name = "pathStr") String pathStr){
password.setPathStr(pathStr);
}


}