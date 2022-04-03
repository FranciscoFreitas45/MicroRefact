package edu.xr.campusweibo.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class MyUserServiceController {

 private MyUserService myuserservice;


@GetMapping
("/getUserById")
public MyUser getUserById(@RequestParam(name = "id") Long id){
  return myuserservice.getUserById(id);
}


}