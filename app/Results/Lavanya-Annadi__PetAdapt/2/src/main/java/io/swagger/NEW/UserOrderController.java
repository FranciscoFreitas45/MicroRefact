package io.swagger.NEW;
 import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import io.swagger.model.User;
@RestController
@CrossOrigin
public class UserOrderController {

@Autowired
 private UserOrderService userorderservice;


@GetMapping
("/Order/{id}/User/getUser")
public User getUser(@PathVariable(name="id") Long id){
return userorderservice.getUser(id);
}


@PutMapping
("/Order/{id}/User/setUser")
public void setUser(@PathVariable(name="id") Long id,@RequestBody User user){
userorderservice.setUser(id,user);
}


}