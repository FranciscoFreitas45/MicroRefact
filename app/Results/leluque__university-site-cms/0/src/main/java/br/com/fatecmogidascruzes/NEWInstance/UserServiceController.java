package br.com.fatecmogidascruzes.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class UserServiceController {

 private UserService userservice;


@GetMapping
("/getByUsername")
public Optional<User> getByUsername(@RequestParam(name = "name") String name){
  return userservice.getByUsername(name);
}


}