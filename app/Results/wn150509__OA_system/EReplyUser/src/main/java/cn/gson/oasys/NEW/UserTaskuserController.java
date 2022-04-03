package cn.gson.oasys.NEW;
 import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import cn.gson.oasys.model.entity.user.User;
@RestController
@CrossOrigin
public class UserTaskuserController {

@Autowired
 private UserTaskuserService usertaskuserservice;


@GetMapping
("/Taskuser/{id}/User/getUserId")
public User getUserId(@PathVariable(name="id") Long userIdv2){
return usertaskuserservice.getUserId(userIdv2);
}


@PutMapping
("/Taskuser/{id}/User/setUserId")
public void setUserId(@PathVariable(name="id") Long userIdv2,@RequestBody User userId){
usertaskuserservice.setUserId(userIdv2,userId);
}


}