package cn.maxcj.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class RoleMapperController {

 private RoleMapper rolemapper;


@GetMapping
("/selectById")
public Object selectById(@RequestParam(name = "Object") Object Object){
  return rolemapper.selectById(Object);
}


}