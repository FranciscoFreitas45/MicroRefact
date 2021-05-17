import org.springframework.web.bind.annotation.*;
import lombok.AllArgsConstructor;
@AllArgsConstructor
@RestController
@CrossOrigin
public class RbacUserRightRelationRepositoryController {

 private RbacUserRightRelationRepository rbacuserrightrelationrepository;


@GetMapping
("/findByUserId")
public Optional<RbacUserRightRelation> findByUserId(@RequestParam(name = "userId") Integer userId){
  return rbacuserrightrelationrepository.findByUserId(userId);
}


}