package com.softserve.edu.Resources.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class UserDAOController {

 private UserDAO userdao;


@GetMapping
("/findByEmail")
public User findByEmail(@RequestParam(name = "email") String email){
  return userdao.findByEmail(email);
}


}