package NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class UserRoleRepositoryController {

 private UserRoleRepository userrolerepository;


@GetMapping
("/findByOrgiAndUser")
public List<UserRole> findByOrgiAndUser(@RequestParam(name = "orgi") String orgi,@RequestParam(name = "user") User user){
  return userrolerepository.findByOrgiAndUser(orgi,user);
}


}