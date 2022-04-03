package com.app.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class ScheduleController {

 private Schedule schedule;

 private Schedule schedule;


@PutMapping
("/setString")
public void setString(@RequestParam(name = "string") String string){
schedule.setString(string);
}


@PutMapping
("/setDivision")
public void setDivision(@RequestParam(name = "division") Division division){
schedule.setDivision(division);
}


}