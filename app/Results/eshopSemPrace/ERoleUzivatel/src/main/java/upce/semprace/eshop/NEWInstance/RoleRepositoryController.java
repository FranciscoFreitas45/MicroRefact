package upce.semprace.eshop.NEWInstance;
 import org.springframework.web.bind.annotation.*;
 import java.util.*;
 import upce.semprace.eshop.entity.*;
  import upce.semprace.eshop.repository.*;

  import upce.semprace.eshop.DTO.*;

@RestController
@CrossOrigin
public class RoleRepositoryController {

 private RoleRepository rolerepository;


@GetMapping
("/findByName")
public Optional<Role> findByName(@RequestParam(name = "roleName") RoleName roleName){
  return rolerepository.findByName(roleName);
}

/*
@GetMapping
("/orElseThrow")
public Object orElseThrow(@RequestParam(name = "Object") Object Object){
  return rolerepository.orElseThrow(Object);
}
*/

}