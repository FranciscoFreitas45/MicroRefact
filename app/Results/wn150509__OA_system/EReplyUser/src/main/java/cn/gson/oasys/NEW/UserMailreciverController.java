package cn.gson.oasys.NEW;
 import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import cn.gson.oasys.model.entity.user.User;
@RestController
@CrossOrigin
public class UserMailreciverController {

@Autowired
 private UserMailreciverService usermailreciverservice;


@GetMapping
("/Mailreciver/{id}/User/getReciverId")
public User getReciverId(@PathVariable(name="id") Long userId){
return usermailreciverservice.getReciverId(userId);
}


@PutMapping
("/Mailreciver/{id}/User/setReciverId")
public void setReciverId(@PathVariable(name="id") Long userId,@RequestBody User reciverId){
usermailreciverservice.setReciverId(userId,reciverId);
}


}