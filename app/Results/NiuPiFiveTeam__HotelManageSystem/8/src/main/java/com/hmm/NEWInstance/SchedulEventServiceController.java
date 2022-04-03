package com.hmm.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class SchedulEventServiceController {

 private SchedulEventService scheduleventservice;


@GetMapping
("/findByDTO")
public List<SchedulEvent> findByDTO(@RequestParam(name = "spec") Specification<SchedulEvent> spec){
  return scheduleventservice.findByDTO(spec);
}


@GetMapping
("/findPassDay")
public List<SchedulEvent> findPassDay(){
  return scheduleventservice.findPassDay();
}


@GetMapping
("/findattenceTotalTime")
public float findattenceTotalTime(@RequestParam(name = "userbname") String userbname){
  return scheduleventservice.findattenceTotalTime(userbname);
}


@GetMapping
("/findWorkTotalDay")
public int findWorkTotalDay(@RequestParam(name = "username") String username){
  return scheduleventservice.findWorkTotalDay(username);
}


@GetMapping
("/findTotalPerson")
public Integer findTotalPerson(){
  return scheduleventservice.findTotalPerson();
}


}