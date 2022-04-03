package es.us.isa.ideas.app.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class UserAccountControllerController {

 private UserAccountController useraccountcontroller;


@GetMapping
("/save")
public boolean save(@RequestParam(name = "userAccount") UserAccount userAccount,@RequestParam(name = "oldPass") String oldPass,@RequestParam(name = "newPass") String newPass){
  return useraccountcontroller.save(userAccount,oldPass,newPass);
}


}