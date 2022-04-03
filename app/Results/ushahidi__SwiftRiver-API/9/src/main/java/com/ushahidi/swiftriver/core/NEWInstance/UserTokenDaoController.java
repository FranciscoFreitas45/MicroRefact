package com.ushahidi.swiftriver.core.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class UserTokenDaoController {

 private UserTokenDao usertokendao;


@GetMapping
("/findByToken")
public UserToken findByToken(@RequestParam(name = "token") String token){
  return usertokendao.findByToken(token);
}


@GetMapping
("/create")
public Object create(@RequestParam(name = "Object") Object Object){
  return usertokendao.create(Object);
}


@PutMapping
("/delete")
public void delete(@RequestParam(name = "token") String token){
usertokendao.delete(token);
}


}