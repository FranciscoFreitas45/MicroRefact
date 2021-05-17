import org.springframework.web.bind.annotation.*;
import lombok.AllArgsConstructor;
@AllArgsConstructor
@RestController
@CrossOrigin
public class RbacRoleRepositoryController {

 private RbacRoleRepository rbacrolerepository;


@GetMapping
("/findById")
public Object findById(@RequestParam(name = "Object") Object Object){
  return rbacrolerepository.findById(Object);
}


}