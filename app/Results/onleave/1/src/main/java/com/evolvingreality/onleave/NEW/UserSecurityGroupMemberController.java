package com.evolvingreality.onleave.NEW;
 import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.evolvingreality.onleave.model.User;
@RestController
@CrossOrigin
public class UserSecurityGroupMemberController {

@Autowired
 private UserSecurityGroupMemberService usersecuritygroupmemberservice;


@GetMapping
("/SecurityGroupMember/{id}/User/getUser")
public User getUser(@PathVariable(name="id") Long id){
return usersecuritygroupmemberservice.getUser(id);
}


@PutMapping
("/SecurityGroupMember/{id}/User/setUser")
public void setUser(@PathVariable(name="id") Long id,@RequestBody User user){
usersecuritygroupmemberservice.setUser(id,user);
}


}