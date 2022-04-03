package com.softserve.edu.Resources.NEW;
 import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.softserve.edu.Resources.entity.UserDetails;
@RestController
@CrossOrigin
public class UserDetailsUserController {

@Autowired
 private UserDetailsUserService userdetailsuserservice;


@GetMapping
("/User/{id}/UserDetails/setUserDetails")
public User setUserDetails(@PathVariable(name="id") Long idO4D4,@RequestParam UserDetails userDetails){
return userdetailsuserservice.setUserDetails(idO4D4,userDetails);
}


@GetMapping
("/User/{id}/UserDetails/getUserDetails")
public UserDetails getUserDetails(@PathVariable(name="id") Long idO4D4){
return userdetailsuserservice.getUserDetails(idO4D4);
}


}