package com.gp.cricket.controller;
 import javax.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.gp.cricket.entity.User;
import com.gp.cricket.service.UserService;
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*", maxAge = 3600)
public class UserController {

@Autowired
 private UserService userService;


@PostMapping("user/resetPassword")
public String resetPassword(User user){
    try {
        this.userService.resetPassword(user);
        return "Password changing successfull!";
    } catch (Exception e) {
        System.out.println(e);
        return "Password reseting failed";
    }
}


@PostMapping("user/updateProfile")
public String updateProfile(User user){
    try {
        this.userService.updateUser(user);
        return "Updating successfull!";
    } catch (Exception e) {
        System.out.println(e);
        return "Updating failed";
    }
}


@GetMapping("profile/{userId}")
public ResponseEntity<User> getUserByUserName(String userId){
    User reply = userService.getUserByUserId(userId);
    if (reply != null) {
        return ResponseEntity.ok(reply);
    }
    return ResponseEntity.notFound().build();
}


@GetMapping("user/{email}")
public ResponseEntity<User> getUser(String email){
    User reply = userService.getUser(email);
    if (reply != null) {
        return ResponseEntity.ok(reply);
    }
    return ResponseEntity.notFound().build();
}


public ResponseEntity<User> userRegister(User user){
    return null;
}


@PutMapping("user/deactivate/{userId}")
public ResponseEntity<Integer> userAccountDeactivate(Integer userId){
    Integer result = userService.userAccountDeactivate(userId);
    if (result != null) {
        return ResponseEntity.ok(result);
    }
    return ResponseEntity.badRequest().build();
}


}