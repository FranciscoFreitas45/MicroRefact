package com.fzshuai.NEW;
 import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.fzshuai.po.User;
@RestController
@CrossOrigin
public class UserBlogController {

@Autowired
 private UserBlogService userblogservice;


@GetMapping
("/Blog/{id}/User/getUser")
public User getUser(@PathVariable(name="id") Long id){
return userblogservice.getUser(id);
}


@PutMapping
("/Blog/{id}/User/setUser")
public void setUser(@PathVariable(name="id") Long id,@RequestBody User user){
userblogservice.setUser(id,user);
}


}