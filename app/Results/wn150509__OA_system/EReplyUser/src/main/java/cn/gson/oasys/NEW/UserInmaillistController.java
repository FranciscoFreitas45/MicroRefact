package cn.gson.oasys.NEW;
 import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import cn.gson.oasys.model.entity.user.User;
@RestController
@CrossOrigin
public class UserInmaillistController {

@Autowired
 private UserInmaillistService userinmaillistservice;


@PutMapping
("/Inmaillist/{id}/User/setMailUserid")
public void setMailUserid(@PathVariable(name="id") Long userId,@RequestBody User mailUserid){
userinmaillistservice.setMailUserid(userId,mailUserid);
}


@GetMapping
("/Inmaillist/{id}/User/getMailUserid")
public User getMailUserid(@PathVariable(name="id") Long userId){
return userinmaillistservice.getMailUserid(userId);
}


}