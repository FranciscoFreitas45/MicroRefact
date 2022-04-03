package cn.gson.oasys.NEW;
 import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import cn.gson.oasys.model.entity.user.User;
@RestController
@CrossOrigin
public class UserVoteTitleUserController {

@Autowired
 private UserVoteTitleUserService uservotetitleuserservice;


@GetMapping
("/VoteTitleUser/{id}/User/getUser")
public User getUser(@PathVariable(name="id") Long userId){
return uservotetitleuserservice.getUser(userId);
}


@PutMapping
("/VoteTitleUser/{id}/User/setUser")
public void setUser(@PathVariable(name="id") Long userId,@RequestBody User user){
uservotetitleuserservice.setUser(userId,user);
}


}