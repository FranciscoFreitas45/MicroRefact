package cn.gson.oasys.NEW;
 import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import cn.gson.oasys.model.entity.user.User;
@RestController
@CrossOrigin
public class UserDirectorController {

@Autowired
 private UserDirectorService userdirectorservice;


@PutMapping
("/Director/{id}/User/setMyuser")
public void setMyuser(@PathVariable(name="id") Long userId,@RequestBody User myuser){
userdirectorservice.setMyuser(userId,myuser);
}


@GetMapping
("/Director/{id}/User/getMyuser")
public User getMyuser(@PathVariable(name="id") Long userId){
return userdirectorservice.getMyuser(userId);
}


}