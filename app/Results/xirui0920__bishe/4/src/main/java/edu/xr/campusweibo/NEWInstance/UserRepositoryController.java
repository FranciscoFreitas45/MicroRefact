package edu.xr.campusweibo.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class UserRepositoryController {

 private UserRepository userrepository;


@GetMapping
("/findOneByLogin")
public Optional<User> findOneByLogin(@RequestParam(name = "login") String login){
  return userrepository.findOneByLogin(login);
}


}