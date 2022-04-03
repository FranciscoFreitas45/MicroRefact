package NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class RoleRepositoryController {

 private RoleRepository rolerepository;


@GetMapping
("/findByOrgi")
public List<Role> findByOrgi(@RequestParam(name = "orgi") String orgi){
  return rolerepository.findByOrgi(orgi);
}


}