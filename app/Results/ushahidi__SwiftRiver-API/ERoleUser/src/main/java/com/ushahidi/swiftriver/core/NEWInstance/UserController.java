package com.ushahidi.swiftriver.core.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class UserController {

 private JpaUserDao jpauserdao;


@PutMapping
("/setPassword/{id}")
public void setPassword(@PathVariable(name = "id") long id,@RequestParam(name = "password") String password){
 jpauserdao.setPassword(id,password);
}


@PutMapping
("/setActive/{id}")
public void setActive(@PathVariable(name = "id") long id,@RequestParam(name = "active") Boolean active){
 jpauserdao.setActive(id,active);
}


@PutMapping
("/setName/{id}")
public void setName(@PathVariable(name = "id") long id,@RequestParam(name = "name") String name){
 jpauserdao.setName(id,name);
}


@PutMapping
("/setEmail/{id}")
public void setEmail(@PathVariable(name = "id") long id,@RequestParam(name = "email") String email){
 jpauserdao.setEmail(id,email);
}


@PutMapping
("/setUsername/{id}")
public void setUsername(@PathVariable(name = "id") long id,@RequestParam(name = "username") String username){
 jpauserdao.setUsername(id,username);
}


@PutMapping
("/setExpired/{id}")
public void setExpired(@PathVariable(name = "id") long id,@RequestParam(name = "expired") Boolean expired){
 jpauserdao.setExpired(id,expired);
}


@PutMapping
("/setLocked/{id}")
public void setLocked(@PathVariable(name = "id") long id,@RequestParam(name = "locked") Boolean locked){
 jpauserdao.setLocked(id,locked);
}


@PutMapping
("/setRoles/{id}")
public void setRoles(@PathVariable(name = "id") long id,@RequestParam(name = "roles") Set<Role> roles){
 jpauserdao.setRoles(id,roles);
}


}