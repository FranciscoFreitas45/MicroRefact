package io.swagger.NEW;
 import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import io.swagger.model.User;
@RestController
@CrossOrigin
public class UserSessionController {

@Autowired
 private UserSessionService usersessionservice;


@GetMapping
("/Session/{id}/User/getUser")
public User getUser(@PathVariable(name="id") Long id){
return usersessionservice.getUser(id);
}


@PutMapping
("/Session/{id}/User/setUser")
public void setUser(@PathVariable(name="id") Long id,@RequestBody User user){
usersessionservice.setUser(id,user);
}


}