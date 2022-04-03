package es.us.isa.ideas.app.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class UserAccountRepositoryController {

 private UserAccountRepository useraccountrepository;


@GetMapping
("/findByUsername")
public UserAccount findByUsername(@RequestParam(name = "username") String username){
  return useraccountrepository.findByUsername(username);
}


}