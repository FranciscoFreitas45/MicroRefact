package com.cg.hbm.controller;
 import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.cg.hbm.entites.User;
import com.cg.hbm.exceptions.UserNotFoundException;
import com.cg.hbm.service.IUserService;
@RestController
@RequestMapping("/user")
public class UserController {

@Autowired
 private IUserService uService;


@DeleteMapping("/{user_id}")
public User removeUser(int user_id){
    return uService.removeUser(user_id);
}


@PostMapping("/add")
public ResponseEntity<User> addUser(User user){
    User resultUser = uService.addUser(user);
    return new ResponseEntity<User>(resultUser, HttpStatus.OK);
}


@GetMapping("/{user_id}")
public ResponseEntity<User> showUser(int user_id){
    User h = uService.showUser(user_id);
    if (h != null) {
        return new ResponseEntity<User>(h, HttpStatus.OK);
    } else {
        return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
    }
}


@PutMapping("/user")
public ResponseEntity<User> updateUser(User user){
    User resultUser = uService.updateUser(user.getUser_id(), user);
    return new ResponseEntity<User>(resultUser, HttpStatus.OK);
}


@GetMapping("/all")
public ResponseEntity<List<User>> showAllUsers(){
    List<User> resultUser = uService.showAllUsers();
    return new ResponseEntity<List<User>>(resultUser, HttpStatus.OK);
}


}