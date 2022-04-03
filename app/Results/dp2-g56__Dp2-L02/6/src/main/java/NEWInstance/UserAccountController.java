package NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class UserAccountController {

 private UserAccount useraccount;

 private UserAccount useraccount;


@PutMapping
("/setUsername")
public void setUsername(@RequestParam(name = "username") String username){
useraccount.setUsername(username);
}


@PutMapping
("/setPassword")
public void setPassword(@RequestParam(name = "password") String password){
useraccount.setPassword(password);
}


@PutMapping
("/setAuthorities")
public void setAuthorities(@RequestParam(name = "authorities") Collection<Authority> authorities){
useraccount.setAuthorities(authorities);
}


@PutMapping
("/setIsNotLocked")
public void setIsNotLocked(@RequestParam(name = "isNotLocked") boolean isNotLocked){
useraccount.setIsNotLocked(isNotLocked);
}


}