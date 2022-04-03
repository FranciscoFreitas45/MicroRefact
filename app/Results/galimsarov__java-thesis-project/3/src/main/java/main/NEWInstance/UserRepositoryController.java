package main.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class UserRepositoryController {

 private UserRepository userrepository;


@GetMapping
("/findByEmail")
public User findByEmail(@RequestParam(name = "email") String email){
  return userrepository.findByEmail(email);
}


@GetMapping
("/findById")
public Object findById(@RequestParam(name = "Object") Object Object){
  return userrepository.findById(Object);
}


@GetMapping
("/saveAndFlush")
public Object saveAndFlush(@RequestParam(name = "Object") Object Object){
  return userrepository.saveAndFlush(Object);
}


@GetMapping
("/findByCode")
public User findByCode(@RequestParam(name = "code") String code){
  return userrepository.findByCode(code);
}


@GetMapping
("/getOne")
public Object getOne(@RequestParam(name = "Object") Object Object){
  return userrepository.getOne(Object);
}


@GetMapping
("/isAdmin")
public int isAdmin(@RequestParam(name = "id") int id){
  return userrepository.isAdmin(id);
}


}