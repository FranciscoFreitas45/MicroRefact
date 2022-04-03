package com.yalcin.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class UserDetailImplController {

 private UserDetailImpl userdetailimpl;

 private UserDetailImpl userdetailimpl;


@GetMapping
("/build")
public UserDetailImpl build(@RequestParam(name = "user") User user,@RequestParam(name = "attributes") Map<String,Object> attributes){
  return userdetailimpl.build(user,attributes);
}


}