package cn.gson.oasys.NEW;
 import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import cn.gson.oasys.model.entity.user.User;
@RestController
@CrossOrigin
public class UserNotepaperController {

@Autowired
 private UserNotepaperService usernotepaperservice;


@GetMapping
("/Notepaper/{id}/User/getUserId")
public User getUserId(@PathVariable(name="id") Long userIdv2){
return usernotepaperservice.getUserId(userIdv2);
}


@PutMapping
("/Notepaper/{id}/User/setUserId")
public void setUserId(@PathVariable(name="id") Long userIdv2,@RequestBody User userId){
usernotepaperservice.setUserId(userIdv2,userId);
}


}