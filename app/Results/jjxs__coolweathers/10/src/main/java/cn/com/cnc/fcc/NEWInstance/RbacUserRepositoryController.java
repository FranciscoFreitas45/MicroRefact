package cn.com.cnc.fcc.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class RbacUserRepositoryController {

 private RbacUserRepository rbacuserrepository;


@GetMapping
("/deleteUserNonExistDefault")
public int deleteUserNonExistDefault(){
  return rbacuserrepository.deleteUserNonExistDefault();
}


@GetMapping
("/saveAll")
public Object saveAll(@RequestParam(name = "Object") Object Object){
  return rbacuserrepository.saveAll(Object);
}


@GetMapping
("/findByUserCode")
public List<RbacUser> findByUserCode(@RequestParam(name = "code") String code){
  return rbacuserrepository.findByUserCode(code);
}


@GetMapping
("/findAllNonExistDefault")
public List<RbacUser> findAllNonExistDefault(){
  return rbacuserrepository.findAllNonExistDefault();
}


}