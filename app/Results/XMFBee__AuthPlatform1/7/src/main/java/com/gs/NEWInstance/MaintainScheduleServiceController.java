package com.gs.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class MaintainScheduleServiceController {

 private MaintainScheduleService maintainscheduleservice;


@GetMapping
("/queryScheduleByRecord")
public List<MaintainSchedule> queryScheduleByRecord(@RequestParam(name = "recordId") String recordId){
  return maintainscheduleservice.queryScheduleByRecord(recordId);
}


@GetMapping
("/queryAll")
public Object queryAll(@RequestParam(name = "Object") Object Object){
  return maintainscheduleservice.queryAll(Object);
}


@GetMapping
("/count")
public Object count(@RequestParam(name = "Object") Object Object){
  return maintainscheduleservice.count(Object);
}


@GetMapping
("/queryByPager")
public Object queryByPager(@RequestParam(name = "Object") Object Object){
  return maintainscheduleservice.queryByPager(Object);
}


@GetMapping
("/insert")
public Object insert(@RequestParam(name = "Object") Object Object){
  return maintainscheduleservice.insert(Object);
}


@GetMapping
("/countByDisable")
public Object countByDisable(@RequestParam(name = "Object") Object Object){
  return maintainscheduleservice.countByDisable(Object);
}


@GetMapping
("/queryByPagerDisable")
public Object queryByPagerDisable(@RequestParam(name = "Object") Object Object){
  return maintainscheduleservice.queryByPagerDisable(Object);
}


@GetMapping
("/inactive")
public Object inactive(@RequestParam(name = "Object") Object Object){
  return maintainscheduleservice.inactive(Object);
}


@GetMapping
("/active")
public Object active(@RequestParam(name = "Object") Object Object){
  return maintainscheduleservice.active(Object);
}


@GetMapping
("/update")
public Object update(@RequestParam(name = "Object") Object Object){
  return maintainscheduleservice.update(Object);
}


}