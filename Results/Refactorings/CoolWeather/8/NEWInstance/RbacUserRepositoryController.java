import org.springframework.web.bind.annotation.*;
import lombok.AllArgsConstructor;
@AllArgsConstructor
@RestController
@CrossOrigin
public class RbacUserRepositoryController {

 private RbacUserRepository rbacuserrepository;


@GetMapping
("/findAllNonExistDefault")
public List<RbacUser> findAllNonExistDefault(){
  return rbacuserrepository.findAllNonExistDefault();
}


}