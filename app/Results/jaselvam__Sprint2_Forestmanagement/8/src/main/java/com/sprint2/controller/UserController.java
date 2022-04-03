package com.sprint2.controller;
 import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.sprint2.model.User;
import com.sprint2.service.UserService;
@Controller
// Maps a specific request path or pattern onto a controller
@RequestMapping("/User")
public class UserController implements IUserController{

 private Logger logger;

// To establish relationship with user service
@Autowired
 private  UserService userService;


@GetMapping("/Logout")
@ResponseBody
public String logout(User user){
    // prints the given message in log file whenever user tries to logout.
    logger.info("logout service initialised");
    return userService.logout(user);
}


// @PostMapping is used to insert user records
@PostMapping("/")
@ResponseBody
public User insertUser(User user){
    return userService.insertUser(user);
}


// @DeleteMapping is used to delete user records
@DeleteMapping("/{username}")
@ResponseBody
public boolean deleteUser(String userName){
    return userService.deleteAdminbyName(userName);
}


@GetMapping("/Login")
@ResponseBody
public String login(User user){
    // prints the given message in log file whenever user tries to login.
    logger.debug("login service initialised");
    logger.info("login service initialised");
    return userService.login(user);
}


}