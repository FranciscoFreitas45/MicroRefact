package cn.gson.oasys.NEW;
 import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import cn.gson.oasys.model.entity.user.User;
@RestController
@CrossOrigin
public class UserTasklistController {

@Autowired
 private UserTasklistService usertasklistservice;


@GetMapping
("/Tasklist/{id}/User/getUsersId")
public User getUsersId(@PathVariable(name="id") Long userId){
return usertasklistservice.getUsersId(userId);
}


@PutMapping
("/Tasklist/{id}/User/setUsersId")
public void setUsersId(@PathVariable(name="id") Long userId,@RequestBody User usersId){
usertasklistservice.setUsersId(userId,usersId);
}


}