package pl.edu.wat.wcy.pz.restaurantServer.NEWInstance;
 import org.springframework.web.bind.annotation.*;
 import pl.edu.wat.wcy.pz.restaurantServer.repository.RoleRepository;
  import pl.edu.wat.wcy.pz.restaurantServer.entity.Role;
 import java.util.*;
@RestController
@CrossOrigin
public class RoleRepositoryController {

 private RoleRepository rolerepository;


@GetMapping
("/findByRoleName")
public Optional<Role> findByRoleName(@RequestParam(name = "roleName") String roleName){
  return rolerepository.findByRoleName(roleName);
}


}