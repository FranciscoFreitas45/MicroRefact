package cn.maxcj.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class IMenuServiceController {

 private IMenuService imenuservice;


@GetMapping
("/getMenusByRoleIds")
public List<MenuNode> getMenusByRoleIds(@RequestParam(name = "roleIds") List<Integer> roleIds){
  return imenuservice.getMenusByRoleIds(roleIds);
}


}