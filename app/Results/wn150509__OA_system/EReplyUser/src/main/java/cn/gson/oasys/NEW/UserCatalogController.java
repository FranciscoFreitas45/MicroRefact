package cn.gson.oasys.NEW;
 import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import cn.gson.oasys.model.entity.user.User;
@RestController
@CrossOrigin
public class UserCatalogController {

@Autowired
 private UserCatalogService usercatalogservice;


@GetMapping
("/Catalog/{id}/User/getUser")
public User getUser(@PathVariable(name="id") Long userId){
return usercatalogservice.getUser(userId);
}


@PutMapping
("/Catalog/{id}/User/setUser")
public void setUser(@PathVariable(name="id") Long userId,@RequestBody User user){
usercatalogservice.setUser(userId,user);
}


}