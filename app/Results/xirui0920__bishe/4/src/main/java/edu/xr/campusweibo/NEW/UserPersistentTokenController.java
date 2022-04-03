package edu.xr.campusweibo.NEW;
 import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import edu.xr.campusweibo.domain.User;
@RestController
@CrossOrigin
public class UserPersistentTokenController {

@Autowired
 private UserPersistentTokenService userpersistenttokenservice;


@GetMapping
("/PersistentToken/{id}/User/getUser")
public User getUser(@PathVariable(name="id") Long id){
return userpersistenttokenservice.getUser(id);
}


@PutMapping
("/PersistentToken/{id}/User/setUser")
public void setUser(@PathVariable(name="id") Long id,@RequestBody User user){
userpersistenttokenservice.setUser(id,user);
}


}