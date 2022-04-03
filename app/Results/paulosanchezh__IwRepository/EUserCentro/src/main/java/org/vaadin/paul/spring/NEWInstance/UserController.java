package org.vaadin.paul.spring.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class UserController {

 private User user;


@GetMapping
("/getNombreyApellidos")
public String getNombreyApellidos(){
  return user.getNombreyApellidos();
}


@GetMapping
("/getId")
public int getId(){
  return user.getId();
}


@GetMapping
("/getAuthorities")
public Collection<? extends GrantedAuthority> getAuthorities(){
  return user.getAuthorities();
}


@GetMapping
("/stream")
public Object stream(@RequestParam(name = "Object") Object Object){
  return user.stream(Object);
}


@GetMapping
("/anyMatch")
public Object anyMatch(@RequestParam(name = "Object") Object Object){
  return user.anyMatch(Object);
}


@PutMapping
("/setBaja")
public void setBaja(@RequestParam(name = "baja") boolean baja){
user.setBaja(baja);
}


@GetMapping
("/getDni")
public String getDni(){
  return user.getDni();
}


@PutMapping
("/setUsername")
public void setUsername(@RequestParam(name = "username") String username){
user.setUsername(username);
}


}