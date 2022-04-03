package com.ushahidi.swiftriver.core.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class UserDaoController {

 private UserDao userdao;


@GetMapping
("/create")
public Object create(@RequestParam(name = "Object") Object Object){
  return userdao.create(Object);
}


@GetMapping
("/update")
public Object update(@RequestParam(name = "Object") Object Object){
  return userdao.update(Object);
}


}