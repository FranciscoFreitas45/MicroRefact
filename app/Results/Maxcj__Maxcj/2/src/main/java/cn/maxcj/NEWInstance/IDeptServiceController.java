package cn.maxcj.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class IDeptServiceController {

 private IDeptService ideptservice;


@GetMapping
("/selectById")
public Object selectById(@RequestParam(name = "Object") Object Object){
  return ideptservice.selectById(Object);
}


@GetMapping
("/clublist")
public List<Map<String,Object>> clublist(@RequestParam(name = "categoryId") Integer categoryId,@RequestParam(name = "condition") String condition){
  return ideptservice.clublist(categoryId,condition);
}


}