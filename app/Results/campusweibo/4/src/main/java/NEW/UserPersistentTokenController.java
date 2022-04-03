package NEW;
 import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
@AllArgsConstructor
@RestController
@CrossOrigin
public class UserPersistentTokenController {

 private UserPersistentTokenService userpersistenttokenservice;


@GetMapping
("/PersistentToken/{id}/User/getUser")
public User getUser(@PathVariable(name="id") Long Long){
return userpersistenttokenservice.getUser(Long);
}


@PutMapping
("/PersistentToken/{id}/User/setUser")
public void setUser(@PathVariable(name="id") Long Long,@RequestBody User user){
userpersistenttokenservice.setUser(Long,user);
}


}