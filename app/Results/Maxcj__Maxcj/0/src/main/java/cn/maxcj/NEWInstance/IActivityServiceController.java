package cn.maxcj.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class IActivityServiceController {

 private IActivityService iactivityservice;


@GetMapping
("/clublist")
public List<Activity> clublist(@RequestParam(name = "deptid") Integer deptid,@RequestParam(name = "condition") String condition){
  return iactivityservice.clublist(deptid,condition);
}


@GetMapping
("/deleteById")
public Object deleteById(@RequestParam(name = "Object") Object Object){
  return iactivityservice.deleteById(Object);
}


@GetMapping
("/selectById")
public Object selectById(@RequestParam(name = "Object") Object Object){
  return iactivityservice.selectById(Object);
}


@GetMapping
("/activity_club")
public List<Map<String,Object>> activity_club(@RequestParam(name = "deptid") Integer deptid,@RequestParam(name = "condition") String condition){
  return iactivityservice.activity_club(deptid,condition);
}


}