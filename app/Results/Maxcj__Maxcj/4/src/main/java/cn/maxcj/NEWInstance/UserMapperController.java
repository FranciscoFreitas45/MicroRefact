package cn.maxcj.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class UserMapperController {

 private UserMapper usermapper;


@GetMapping
("/selectById")
public Object selectById(@RequestParam(name = "Object") Object Object){
  return usermapper.selectById(Object);
}


}