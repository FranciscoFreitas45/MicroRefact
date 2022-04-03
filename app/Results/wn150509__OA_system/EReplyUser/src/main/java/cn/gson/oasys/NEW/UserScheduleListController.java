package cn.gson.oasys.NEW;
 import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import cn.gson.oasys.model.entity.user.User;
@RestController
@CrossOrigin
public class UserScheduleListController {

@Autowired
 private UserScheduleListService userschedulelistservice;


@GetMapping
("/ScheduleList/{id}/User/getUser")
public User getUser(@PathVariable(name="id") Long userId){
return userschedulelistservice.getUser(userId);
}


@PutMapping
("/ScheduleList/{id}/User/setUser")
public void setUser(@PathVariable(name="id") Long userId,@RequestBody User user){
userschedulelistservice.setUser(userId,user);
}


}