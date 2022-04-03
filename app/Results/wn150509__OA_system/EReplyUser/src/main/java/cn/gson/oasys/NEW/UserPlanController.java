package cn.gson.oasys.NEW;
 import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import cn.gson.oasys.model.entity.user.User;
@RestController
@CrossOrigin
public class UserPlanController {

@Autowired
 private UserPlanService userplanservice;


@GetMapping
("/Plan/{id}/User/getUser")
public User getUser(@PathVariable(name="id") Long userId){
return userplanservice.getUser(userId);
}


@PutMapping
("/Plan/{id}/User/setUser")
public void setUser(@PathVariable(name="id") Long userId,@RequestBody User user){
userplanservice.setUser(userId,user);
}


}