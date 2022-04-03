package cn.com.cnc.fcc.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class RbacRoleRepositoryController {

 private RbacRoleRepository rbacrolerepository;


@GetMapping
("/findByIdAndDelFlag")
public List<RbacRole> findByIdAndDelFlag(@RequestParam(name = "id") Long id,@RequestParam(name = "flag") Integer flag){
  return rbacrolerepository.findByIdAndDelFlag(id,flag);
}


@GetMapping
("/findByRoleCode")
public List<RbacRole> findByRoleCode(@RequestParam(name = "s") String s){
  return rbacrolerepository.findByRoleCode(s);
}


@GetMapping
("/findById")
public Object findById(@RequestParam(name = "Object") Object Object){
  return rbacrolerepository.findById(Object);
}


}