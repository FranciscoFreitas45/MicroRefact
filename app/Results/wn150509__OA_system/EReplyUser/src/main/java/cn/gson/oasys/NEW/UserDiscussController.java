package cn.gson.oasys.NEW;
 import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import cn.gson.oasys.model.entity.user.User;
@RestController
@CrossOrigin
public class UserDiscussController {

@Autowired
 private UserDiscussService userdiscussservice;


@GetMapping
("/Discuss/{id}/User/getUser")
public User getUser(@PathVariable(name="id") Long userId){
return userdiscussservice.getUser(userId);
}


@PutMapping
("/Discuss/{id}/User/setUser")
public void setUser(@PathVariable(name="id") Long userId,@RequestBody User user){
userdiscussservice.setUser(userId,user);
}


}