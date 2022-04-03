package com.app.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class ScheduleServiceController {

 private ScheduleService scheduleservice;


@GetMapping
("/fordivision")
public Schedule fordivision(@RequestParam(name = "divID") int divID){
  return scheduleservice.fordivision(divID);
}


@PutMapping
("/create")
public void create(@RequestParam(name = "schedule") Schedule schedule){
scheduleservice.create(schedule);
}


@PutMapping
("/update")
public void update(@RequestParam(name = "schedule") Schedule schedule){
scheduleservice.update(schedule);
}


}