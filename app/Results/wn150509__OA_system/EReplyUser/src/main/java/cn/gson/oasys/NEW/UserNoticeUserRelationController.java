package cn.gson.oasys.NEW;
 import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import cn.gson.oasys.model.entity.user.User;
@RestController
@CrossOrigin
public class UserNoticeUserRelationController {

@Autowired
 private UserNoticeUserRelationService usernoticeuserrelationservice;


@GetMapping
("/NoticeUserRelation/{id}/User/getUserId")
public User getUserId(@PathVariable(name="id") Long userIdv2){
return usernoticeuserrelationservice.getUserId(userIdv2);
}


@PutMapping
("/NoticeUserRelation/{id}/User/setUserId")
public void setUserId(@PathVariable(name="id") Long userIdv2,@RequestBody User userId){
usernoticeuserrelationservice.setUserId(userIdv2,userId);
}


}