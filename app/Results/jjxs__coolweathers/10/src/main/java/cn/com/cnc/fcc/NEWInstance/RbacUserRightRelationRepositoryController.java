package cn.com.cnc.fcc.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class RbacUserRightRelationRepositoryController {

 private RbacUserRightRelationRepository rbacuserrightrelationrepository;


@GetMapping
("/findByRoleId")
public List<RbacUserRightRelation> findByRoleId(@RequestParam(name = "id") Integer id){
  return rbacuserrightrelationrepository.findByRoleId(id);
}


@GetMapping
("/findByUserId")
public Optional<RbacUserRightRelation> findByUserId(@RequestParam(name = "userId") Integer userId){
  return rbacuserrightrelationrepository.findByUserId(userId);
}


}