package com.yalcin.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class UserController {

 private User user;


@PutMapping
("/setName/{id}")
public void setName(@PathVariable(name = "id") Integer id,@RequestParam(name = "name") String name){
 userrepository.setName(id,name);
}


@PutMapping
("/setLastname/{id}")
public void setLastname(@PathVariable(name = "id") Integer id,@RequestParam(name = "lastname") String lastname){
 userrepository.setLastname(id,lastname);
}


@PutMapping
("/setAge/{id}")
public void setAge(@PathVariable(name = "id") Integer id,@RequestParam(name = "age") String age){
 userrepository.setAge(id,age);
}


@PutMapping
("/setEmail/{id}")
public void setEmail(@PathVariable(name = "id") Integer id,@RequestParam(name = "email") String email){
 userrepository.setEmail(id,email);
}


@PutMapping
("/setEnabled/{id}")
public void setEnabled(@PathVariable(name = "id") Integer id,@RequestParam(name = "enabled") boolean enabled){
 userrepository.setEnabled(id,enabled);
}


@PutMapping
("/setRoles/{id}")
public void setRoles(@PathVariable(name = "id") Integer id,@RequestParam(name = "roles") Set<Role> roles){
 userrepository.setRoles(id,roles);
}


@PutMapping
("/setActiveSessions/{id}")
public void setActiveSessions(@PathVariable(name = "id") Integer id,@RequestParam(name = "activeSessions") Set<ActiveSessions> activeSessions){
 userrepository.setActiveSessions(id,activeSessions);
}


@GetMapping
("/isEnabled/{id}")
public boolean isEnabled(@PathVariable(name = "id") Integer id){
 return userrepository.isEnabled(id);
}


@PutMapping
("/addActiveSession/{id}")
public void addActiveSession(@PathVariable(name = "id") Integer id,@RequestParam(name = "activeSession") ActiveSessions activeSession){
 userrepository.addActiveSession(id,activeSession);
}


}