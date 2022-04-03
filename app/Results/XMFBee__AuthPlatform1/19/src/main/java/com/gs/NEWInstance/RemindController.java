package com.gs.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class RemindController {

 private Remind remind;

 private Remind remind;


@PutMapping
("/setRemindUser")
public void setRemindUser(@RequestParam(name = "remindUser") String remindUser){
remind.setRemindUser(remindUser);
}


}