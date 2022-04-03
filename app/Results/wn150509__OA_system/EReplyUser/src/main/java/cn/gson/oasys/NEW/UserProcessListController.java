package cn.gson.oasys.NEW;
 import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import cn.gson.oasys.model.entity.user.User;
@RestController
@CrossOrigin
public class UserProcessListController {

@Autowired
 private UserProcessListService userprocesslistservice;


@GetMapping
("/ProcessList/{id}/User/getUserId")
public User getUserId(@PathVariable(name="id") Long userIdv2){
return userprocesslistservice.getUserId(userIdv2);
}


@PutMapping
("/ProcessList/{id}/User/setUserId")
public void setUserId(@PathVariable(name="id") Long userIdv2,@RequestBody User userId){
userprocesslistservice.setUserId(userIdv2,userId);
}


}