package cn.maxcj.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class IApplyServiceController {

 private IApplyService iapplyservice;


@GetMapping
("/apply_exist")
public boolean apply_exist(@RequestParam(name = "userid") Integer userid){
  return iapplyservice.apply_exist(userid);
}


@GetMapping
("/selectById")
public Object selectById(@RequestParam(name = "Object") Object Object){
  return iapplyservice.selectById(Object);
}


@GetMapping
("/insert")
public Object insert(@RequestParam(name = "Object") Object Object){
  return iapplyservice.insert(Object);
}


}