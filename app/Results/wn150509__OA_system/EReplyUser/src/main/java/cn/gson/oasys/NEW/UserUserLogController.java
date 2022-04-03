package cn.gson.oasys.NEW;
 import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import cn.gson.oasys.model.entity.user.User;
@RestController
@CrossOrigin
public class UserUserLogController {

@Autowired
 private UserUserLogService useruserlogservice;


@GetMapping
("/UserLog/{id}/User/getUser")
public User getUser(@PathVariable(name="id") Long userId){
return useruserlogservice.getUser(userId);
}


@PutMapping
("/UserLog/{id}/User/setUser")
public void setUser(@PathVariable(name="id") Long userId,@RequestBody User user){
useruserlogservice.setUser(userId,user);
}


}