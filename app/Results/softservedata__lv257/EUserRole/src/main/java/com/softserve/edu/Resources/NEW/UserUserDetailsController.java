package com.softserve.edu.Resources.NEW;
 import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.softserve.edu.Resources.entity.User;
@RestController
@CrossOrigin
public class UserUserDetailsController {

@Autowired
 private UserUserDetailsService useruserdetailsservice;


@GetMapping
("/UserDetails/{id}/User/getUser")
public User getUser(@PathVariable(name="id") Long idO2KS){
return useruserdetailsservice.getUser(idO2KS);
}


@PutMapping
("/UserDetails/{id}/User/setUser")
public void setUser(@PathVariable(name="id") Long idO2KS,@RequestBody User user){
useruserdetailsservice.setUser(idO2KS,user);
}


}