package com.NEWInstance;

import com.DTO.User;
import com.pl.edu.wat.wcy.pz.restaurantServer.service.UserService;
import org.springframework.web.bind.annotation.*;
import lombok.AllArgsConstructor;

import java.util.Optional;

@AllArgsConstructor
@RestController
@CrossOrigin
public class UserServiceController {

 private UserService userservice;


@GetMapping
("/getUserById")
public Optional<User> getUserById(@RequestParam(name = "id") Long id){
  return userservice.getUserById(id);
}


}