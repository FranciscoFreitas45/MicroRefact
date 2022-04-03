package cn.gson.oasys.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class DaymanageDaoController {

 private DaymanageDao daymanagedao;


@GetMapping
("/findByUser")
public Page<ScheduleList> findByUser(@RequestParam(name = "user") User user,@RequestParam(name = "pa") Pageable pa){
  return daymanagedao.findByUser(user,pa);
}


@GetMapping
("/findByUsers")
public Page<ScheduleList> findByUsers(@RequestParam(name = "users") List<User> users,@RequestParam(name = "pa") Pageable pa){
  return daymanagedao.findByUsers(users,pa);
}


@GetMapping
("/findOne")
public Object findOne(@RequestParam(name = "Object") Object Object){
  return daymanagedao.findOne(Object);
}


@GetMapping
("/save")
public Object save(@RequestParam(name = "Object") Object Object){
  return daymanagedao.save(Object);
}


@GetMapping
("/delete")
public Object delete(@RequestParam(name = "Object") Object Object){
  return daymanagedao.delete(Object);
}


}