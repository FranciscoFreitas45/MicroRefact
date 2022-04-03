package com.ec.survey.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class AccessController {

 private Access access;

 private Access access;


@PutMapping
("/setSurvey")
public void setSurvey(@RequestParam(name = "survey") Survey survey){
access.setSurvey(survey);
}


@PutMapping
("/setUser")
public void setUser(@RequestParam(name = "user") User user){
access.setUser(user);
}


}