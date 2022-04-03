package pl.szymanski.sharelibrary.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class UserServiceController {

 private UserService userservice;


@GetMapping
("/getUserById")
public User getUserById(@RequestParam(name = "id") Long id){
  return userservice.getUserById(id);
}


}