package cn.maxcj.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class MenuMapperController {

 private MenuMapper menumapper;


@GetMapping
("/selectById")
public Object selectById(@RequestParam(name = "Object") Object Object){
  return menumapper.selectById(Object);
}


@GetMapping
("/selectOne")
public Object selectOne(@RequestParam(name = "Object") Object Object){
  return menumapper.selectOne(Object);
}


@GetMapping
("/getResUrlsByRoleId")
public List<String> getResUrlsByRoleId(@RequestParam(name = "roleId") Integer roleId){
  return menumapper.getResUrlsByRoleId(roleId);
}


}