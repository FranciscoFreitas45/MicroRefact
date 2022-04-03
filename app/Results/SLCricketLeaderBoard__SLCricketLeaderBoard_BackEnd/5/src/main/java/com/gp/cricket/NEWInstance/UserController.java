package com.gp.cricket.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class UserController {

 private UserRepository userrepository;


@PutMapping
("/setStatus/{id}")
public void setStatus(@PathVariable(name = "id") Integer id,@RequestParam(name = "status") Byte status){
 userrepository.setStatus(id,status);
}


}