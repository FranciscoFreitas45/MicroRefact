package cn.gson.oasys.NEW;
 import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import cn.gson.oasys.model.entity.user.User;
@RestController
@CrossOrigin
public class UserLoginRecordController {

@Autowired
 private UserLoginRecordService userloginrecordservice;


@GetMapping
("/LoginRecord/{id}/User/getUser")
public User getUser(@PathVariable(name="id") Long userId){
return userloginrecordservice.getUser(userId);
}


@PutMapping
("/LoginRecord/{id}/User/setUser")
public void setUser(@PathVariable(name="id") Long userId,@RequestBody User user){
userloginrecordservice.setUser(userId,user);
}


}