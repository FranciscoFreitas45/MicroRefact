package cn.gson.oasys.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class ScheduleListController {

 private DaymanageDao daymanagedao;


@PutMapping
("/setUser/{id}")
public void setUser(@PathVariable(name = "id") Long id,@RequestParam(name = "user") User user){
 daymanagedao.setUser(id,user);
}


@PutMapping
("/setUsers/{id}")
public void setUsers(@PathVariable(name = "id") Long id,@RequestParam(name = "users") List<User> users){
 daymanagedao.setUsers(id,users);
}


@PutMapping
("/setUsername/{id}")
public void setUsername(@PathVariable(name = "id") Long id,@RequestParam(name = "username") String username){
 daymanagedao.setUsername(id,username);
}


@PutMapping
("/setIsreminded/{id}")
public void setIsreminded(@PathVariable(name = "id") Long id,@RequestParam(name = "isreminded") Boolean isreminded){
 daymanagedao.setIsreminded(id,isreminded);
}


}