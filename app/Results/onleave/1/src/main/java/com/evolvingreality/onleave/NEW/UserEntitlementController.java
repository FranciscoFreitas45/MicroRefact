package com.evolvingreality.onleave.NEW;
 import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.evolvingreality.onleave.model.User;
@RestController
@CrossOrigin
public class UserEntitlementController {

@Autowired
 private UserEntitlementService userentitlementservice;


@GetMapping
("/Entitlement/{id}/User/getUser")
public User getUser(@PathVariable(name="id") Long id){
return userentitlementservice.getUser(id);
}


@PutMapping
("/Entitlement/{id}/User/setUser")
public void setUser(@PathVariable(name="id") Long id,@RequestBody User user){
userentitlementservice.setUser(id,user);
}


}