package cn.gson.oasys.NEW;
 import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import cn.gson.oasys.model.entity.user.User;
@RestController
@CrossOrigin
public class UserMailnumberController {

@Autowired
 private UserMailnumberService usermailnumberservice;


@GetMapping
("/Mailnumber/{id}/User/getMailUserId")
public User getMailUserId(@PathVariable(name="id") Long userId){
return usermailnumberservice.getMailUserId(userId);
}


@PutMapping
("/Mailnumber/{id}/User/setMailUserId")
public void setMailUserId(@PathVariable(name="id") Long userId,@RequestBody User mailUserId){
usermailnumberservice.setMailUserId(userId,mailUserId);
}


}