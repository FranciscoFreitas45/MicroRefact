package com.gp.cricket.controller;
 import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.gp.cricket.entity.Referee;
import com.gp.cricket.entity.User;
import com.gp.cricket.service.RefereeService;
import com.gp.cricket.service.UserService;
import com.gp.cricket.Interface.UserService;
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*", maxAge = 3600)
public class RefereeController {

@Autowired
 private RefereeService refereeService;

@Autowired
 private UserService userService;


@PostMapping("/refereeRegister")
public Referee saveStudent(User user){
    System.out.println(user);
    return refereeService.saveReferee(user);
}


@GetMapping("/referee/available")
public List<Referee> getAvailableReferees(){
    return refereeService.getAvailableReferees();
}


@GetMapping("/referees")
public List<Referee> getAllReferees(){
    return this.refereeService.getAllReferees();
}


}