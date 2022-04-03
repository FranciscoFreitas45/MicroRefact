package cn.gson.oasys.NEW;
 import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import cn.gson.oasys.model.entity.user.User;
@RestController
@CrossOrigin
public class UserStayController {

@Autowired
 private UserStayService userstayservice;


@GetMapping
("/Stay/{id}/User/getUser")
public User getUser(@PathVariable(name="id") Long userId){
return userstayservice.getUser(userId);
}


@PutMapping
("/Stay/{id}/User/setUser")
public void setUser(@PathVariable(name="id") Long userId,@RequestBody User user){
userstayservice.setUser(userId,user);
}


}