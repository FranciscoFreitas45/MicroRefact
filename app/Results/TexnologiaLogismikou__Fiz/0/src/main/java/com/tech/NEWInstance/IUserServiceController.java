package com.tech.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class IUserServiceController {

 private IUserService iuserservice;


@GetMapping
("/checkUsername")
public boolean checkUsername(@RequestParam(name = "username") String username){
  return iuserservice.checkUsername(username);
}


@GetMapping
("/getUserByUsername")
public User getUserByUsername(@RequestParam(name = "username") String username){
  return iuserservice.getUserByUsername(username);
}


@GetMapping
("/getUserById")
public User getUserById(@RequestParam(name = "id") Long id){
  return iuserservice.getUserById(id);
}


@PutMapping
("/updateUserRoom")
public void updateUserRoom(@RequestParam(name = "hasRoom") boolean hasRoom,@RequestParam(name = "userid") Long userid){
iuserservice.updateUserRoom(hasRoom,userid);
}


}