package com.gp.cricket.NEW;
 import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.gp.cricket.entity.User;
@RestController
@CrossOrigin
public class UserRefereeController {

@Autowired
 private UserRefereeService userrefereeservice;


@GetMapping
("/Referee/{id}/User/getUserId")
public User getUserId(@PathVariable(name="id") Integer userIdv2){
return userrefereeservice.getUserId(userIdv2);
}


@PutMapping
("/Referee/{id}/User/setUserId")
public void setUserId(@PathVariable(name="id") Integer userIdv2,@RequestBody User userId){
userrefereeservice.setUserId(userIdv2,userId);
}


}