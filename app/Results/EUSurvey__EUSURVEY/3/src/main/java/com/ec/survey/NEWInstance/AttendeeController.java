package com.ec.survey.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class AttendeeController {

 private Attendee attendee;

 private Attendee attendee;


@PutMapping
("/setRegFormId")
public void setRegFormId(@RequestParam(name = "regFormId") Integer regFormId){
attendee.setRegFormId(regFormId);
}


@PutMapping
("/setEmail")
public void setEmail(@RequestParam(name = "email") String email){
attendee.setEmail(email);
}


@PutMapping
("/setName")
public void setName(@RequestParam(name = "name") String name){
attendee.setName(name);
}


}