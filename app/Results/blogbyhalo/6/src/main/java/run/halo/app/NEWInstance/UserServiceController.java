package run.halo.app.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class UserServiceController {

 private UserService userservice;


@GetMapping
("/getCurrentUser")
public Optional<User> getCurrentUser(){
  return userservice.getCurrentUser();
}


@GetMapping
("/orElseThrow")
public Object orElseThrow(@RequestParam(name = "Object") Object Object){
  return userservice.orElseThrow(Object);
}


@GetMapping
("/getByEmail")
public Optional<User> getByEmail(@RequestParam(name = "email") String email){
  return userservice.getByEmail(email);
}


@GetMapping
("/createBy")
public User createBy(@RequestParam(name = "userParam") UserParam userParam){
  return userservice.createBy(userParam);
}


@PutMapping
("/setPassword")
public void setPassword(@RequestParam(name = "user") User user,@RequestParam(name = "plainPassword") String plainPassword){
userservice.setPassword(user,plainPassword);
}


@GetMapping
("/update")
public Object update(@RequestParam(name = "Object") Object Object){
  return userservice.update(Object);
}


@GetMapping
("/map")
public Object map(@RequestParam(name = "Object") Object Object){
  return userservice.map(Object);
}


@GetMapping
("/listAll")
public Object listAll(@RequestParam(name = "Object") Object Object){
  return userservice.listAll(Object);
}


@GetMapping
("/create")
public Object create(@RequestParam(name = "Object") Object Object){
  return userservice.create(Object);
}


@GetMapping
("/orElse")
public Object orElse(@RequestParam(name = "Object") Object Object){
  return userservice.orElse(Object);
}


}