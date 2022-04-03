package cn.gson.oasys.NEW;
 import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import cn.gson.oasys.model.entity.user.User;
@RestController
@CrossOrigin
public class UserFileListController {

@Autowired
 private UserFileListService userfilelistservice;


@GetMapping
("/FileList/{id}/User/getUser")
public User getUser(@PathVariable(name="id") Long userId){
return userfilelistservice.getUser(userId);
}


@PutMapping
("/FileList/{id}/User/setUser")
public void setUser(@PathVariable(name="id") Long userId,@RequestBody User user){
userfilelistservice.setUser(userId,user);
}


}