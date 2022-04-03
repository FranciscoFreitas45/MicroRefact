package es.us.isa.ideas.app.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class UserAccountServiceController {

 private UserAccountService useraccountservice;


@GetMapping
("/findAll")
public Collection<UserAccount> findAll(){
  return useraccountservice.findAll();
}


@GetMapping
("/create")
public UserAccount create(@RequestParam(name = "actor") Actor actor){
  return useraccountservice.create(actor);
}


@GetMapping
("/findByUsername")
public UserAccount findByUsername(@RequestParam(name = "username") String username){
  return useraccountservice.findByUsername(username);
}


@GetMapping
("/findById")
public Object findById(@RequestParam(name = "Object") Object Object){
  return useraccountservice.findById(Object);
}


@PutMapping
("/resetPassword")
public void resetPassword(@RequestParam(name = "account") UserAccount account,@RequestParam(name = "notificationEmail") String notificationEmail){
useraccountservice.resetPassword(account,notificationEmail);
}


}