package com.ITBlog.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class UserServiceController {

 private UserService userservice;


@GetMapping
("/getTypeByUserId")
public int getTypeByUserId(@RequestParam(name = "userId") long userId){
  return userservice.getTypeByUserId(userId);
}


@GetMapping
("/saveUser")
public int saveUser(@RequestParam(name = "name") String name,@RequestParam(name = "gender") int gender,@RequestParam(name = "age") int age,@RequestParam(name = "password") String password,@RequestParam(name = "phone") long phone){
  return userservice.saveUser(name,gender,age,password,phone);
}


@GetMapping
("/deleteUser")
public int deleteUser(@RequestParam(name = "userId") long userId){
  return userservice.deleteUser(userId);
}


@GetMapping
("/closeUserAccount")
public int closeUserAccount(@RequestParam(name = "userId") long userId){
  return userservice.closeUserAccount(userId);
}


@GetMapping
("/updatePassword")
public int updatePassword(@RequestParam(name = "userId") long userId,@RequestParam(name = "password") String password){
  return userservice.updatePassword(userId,password);
}


}