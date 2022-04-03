package cn.gson.oasys.NEW;
 import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import cn.gson.oasys.model.entity.user.User;
@RestController
@CrossOrigin
public class UserTrafficController {

@Autowired
 private UserTrafficService usertrafficservice;


@GetMapping
("/Traffic/{id}/User/getUser")
public User getUser(@PathVariable(name="id") Long userId){
return usertrafficservice.getUser(userId);
}


@PutMapping
("/Traffic/{id}/User/setUser")
public void setUser(@PathVariable(name="id") Long userId,@RequestBody User user){
usertrafficservice.setUser(userId,user);
}


}