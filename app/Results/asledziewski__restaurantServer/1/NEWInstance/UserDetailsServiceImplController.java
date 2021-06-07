import org.springframework.web.bind.annotation.*;
import lombok.AllArgsConstructor;
@AllArgsConstructor
@RestController
@CrossOrigin
public class UserDetailsServiceImplController {

 private UserDetailsServiceImpl userdetailsserviceimpl;


@GetMapping
("/loadUserByUsername")
public UserDetails loadUserByUsername(@RequestParam(name = "s") String s){
  return userdetailsserviceimpl.loadUserByUsername(s);
}


}