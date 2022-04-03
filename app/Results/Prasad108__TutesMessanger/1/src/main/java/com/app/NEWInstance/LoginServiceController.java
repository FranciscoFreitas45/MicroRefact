package com.app.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class LoginServiceController {

 private LoginService loginservice;


@GetMapping
("/find")
public Login find(@RequestParam(name = "id") int id){
  return loginservice.find(id);
}


@PutMapping
("/delet")
public void delet(@RequestParam(name = "login") Login login){
loginservice.delet(login);
}


@PutMapping
("/update")
public void update(@RequestParam(name = "login") Login login){
loginservice.update(login);
}


@PutMapping
("/create")
public void create(@RequestParam(name = "login") Login login){
loginservice.create(login);
}


}