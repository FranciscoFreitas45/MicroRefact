package pl.edu.wat.wcy.pz.restaurantServer.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class UserServiceController {

 private UserService userservice;


@GetMapping
("/getUserById")
public Optional<User> getUserById(@RequestParam(name = "id") Long id){
  return userservice.getUserById(id);
}


}