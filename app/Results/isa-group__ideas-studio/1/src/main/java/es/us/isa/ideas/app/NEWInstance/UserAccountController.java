package es.us.isa.ideas.app.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class UserAccountController {

 private UserAccount useraccount;

 private UserAccount useraccount;


@PutMapping
("/setAuthorities")
public void setAuthorities(@RequestParam(name = "authorities") Collection<Authority> authorities){
useraccount.setAuthorities(authorities);
}


}