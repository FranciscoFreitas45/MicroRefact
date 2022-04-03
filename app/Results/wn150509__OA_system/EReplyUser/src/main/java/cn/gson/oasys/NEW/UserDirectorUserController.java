package cn.gson.oasys.NEW;
 import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import cn.gson.oasys.model.entity.user.User;
@RestController
@CrossOrigin
public class UserDirectorUserController {

@Autowired
 private UserDirectorUserService userdirectoruserservice;


@GetMapping
("/DirectorUser/{id}/User/getShareuser")
public User getShareuser(@PathVariable(name="id") Long userId){
return userdirectoruserservice.getShareuser(userId);
}


@GetMapping
("/DirectorUser/{id}/User/getUser")
public User getUser(@PathVariable(name="id") Long userId){
return userdirectoruserservice.getUser(userId);
}


@PutMapping
("/DirectorUser/{id}/User/setShareuser")
public void setShareuser(@PathVariable(name="id") Long userId,@RequestBody User shareuser){
userdirectoruserservice.setShareuser(userId,shareuser);
}


@PutMapping
("/DirectorUser/{id}/User/setUser")
public void setUser(@PathVariable(name="id") Long userId,@RequestBody User user){
userdirectoruserservice.setUser(userId,user);
}


}