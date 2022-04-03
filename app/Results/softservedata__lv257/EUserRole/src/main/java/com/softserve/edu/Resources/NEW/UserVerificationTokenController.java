package com.softserve.edu.Resources.NEW;
 import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.softserve.edu.Resources.entity.User;
@RestController
@CrossOrigin
public class UserVerificationTokenController {

@Autowired
 private UserVerificationTokenService userverificationtokenservice;


@GetMapping
("/VerificationToken/{id}/User/getUser")
public User getUser(@PathVariable(name="id") Long idXLHJ){
return userverificationtokenservice.getUser(idXLHJ);
}


@GetMapping
("/VerificationToken/{id}/User/setUser")
public VerificationToken setUser(@PathVariable(name="id") Long idXLHJ,@RequestParam User user){
return userverificationtokenservice.setUser(idXLHJ,user);
}


}