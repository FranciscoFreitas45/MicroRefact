package ink.champ.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class UserController {

 private User user;


@GetMapping
("/getId")
public Long getId(){
  return user.getId();
}


@GetMapping
("/equals")
public Object equals(@RequestParam(name = "Object") Object Object){
  return user.equals(Object);
}


@GetMapping
("/isAdmin/{id}")
public boolean isAdmin(@PathVariable(name = "id") Long id){
 return userrepository.isAdmin(id);
}


}