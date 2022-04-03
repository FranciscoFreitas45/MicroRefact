package cn.gson.oasys.NEW;
 import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import cn.gson.oasys.model.entity.user.User;
@RestController
@CrossOrigin
public class UserReviewedController {

@Autowired
 private UserReviewedService userreviewedservice;


@GetMapping
("/Reviewed/{id}/User/getUserId")
public User getUserId(@PathVariable(name="id") Long userIdv2){
return userreviewedservice.getUserId(userIdv2);
}


@PutMapping
("/Reviewed/{id}/User/setUserId")
public void setUserId(@PathVariable(name="id") Long userIdv2,@RequestBody User userId){
userreviewedservice.setUserId(userIdv2,userId);
}


}