package com.softserve.edu.Resources.rest;
 import com.softserve.edu.Resources.service.UserService;
import com.softserve.edu.Resources.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
@RestController
@RequestMapping("/API")
public class UserRestController {

@Autowired
 private UserService userService;


@RequestMapping(value = "/user/", method = RequestMethod.GET)
public ResponseEntity<List<User>> listAllUsers(){
    List<User> users = userService.getAllUsers();
    if (users.isEmpty()) {
        // You many decide to return HttpStatus.NOT_FOUND
        return new ResponseEntity<List<User>>(HttpStatus.NO_CONTENT);
    }
    return new ResponseEntity<List<User>>(users, HttpStatus.OK);
}


}