package cn.gson.oasys.NEW;
 import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import cn.gson.oasys.model.entity.user.User;
@RestController
@CrossOrigin
public class UserAttendsController {

@Autowired
 private UserAttendsService userattendsservice;


@GetMapping
("/Attends/{id}/User/getUser")
public User getUser(@PathVariable(name="id") Long userId){
return userattendsservice.getUser(userId);
}


@PutMapping
("/Attends/{id}/User/setUser")
public void setUser(@PathVariable(name="id") Long userId,@RequestBody User user){
userattendsservice.setUser(userId,user);
}


}