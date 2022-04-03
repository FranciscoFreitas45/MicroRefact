package cn.gson.oasys.NEW;
 import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import cn.gson.oasys.model.entity.user.User;
@RestController
@CrossOrigin
public class UserCommentController {

@Autowired
 private UserCommentService usercommentservice;


@GetMapping
("/Comment/{id}/User/getUser")
public User getUser(@PathVariable(name="id") Long userId){
return usercommentservice.getUser(userId);
}


@PutMapping
("/Comment/{id}/User/setUser")
public void setUser(@PathVariable(name="id") Long userId,@RequestBody User user){
usercommentservice.setUser(userId,user);
}


}