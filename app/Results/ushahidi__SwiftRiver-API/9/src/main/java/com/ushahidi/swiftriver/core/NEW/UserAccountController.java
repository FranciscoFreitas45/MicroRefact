package com.ushahidi.swiftriver.core.NEW;
 import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.ushahidi.swiftriver.core.model.User;
@RestController
@CrossOrigin
public class UserAccountController {

@Autowired
 private UserAccountService useraccountservice;


@GetMapping
("/Account/{id}/User/getOwner")
public User getOwner(@PathVariable(name="id") long idHR3O){
return useraccountservice.getOwner(idHR3O);
}


@PutMapping
("/Account/{id}/User/setOwner")
public void setOwner(@PathVariable(name="id") long idHR3O,@RequestBody User owner){
useraccountservice.setOwner(idHR3O,owner);
}


}