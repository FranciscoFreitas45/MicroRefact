package pl.edu.wat.wcy.pz.restaurantServer.NEWInstance;
 import org.springframework.web.bind.annotation.*;
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