import org.springframework.web.bind.annotation.*;
import lombok.AllArgsConstructor;
@AllArgsConstructor
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