package com.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class JoinCircleThreadController {

 private JoinCircleThread joincirclethread;

 private JoinCircleThread joincirclethread;


@PutMapping
("/setCircleId")
public void setCircleId(@RequestParam(name = "circleId") String circleId){
joincirclethread.setCircleId(circleId);
}


@PutMapping
("/setUser")
public void setUser(@RequestParam(name = "user") User user){
joincirclethread.setUser(user);
}


}