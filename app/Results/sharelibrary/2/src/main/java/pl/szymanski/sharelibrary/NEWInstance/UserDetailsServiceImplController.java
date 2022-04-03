package pl.szymanski.sharelibrary.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class UserDetailsServiceImplController {

 private UserDetailsServiceImpl userdetailsserviceimpl;


@GetMapping
("/loadUserById")
public UserDetails loadUserById(@RequestParam(name = "id") Long id){
  return userdetailsserviceimpl.loadUserById(id);
}


}