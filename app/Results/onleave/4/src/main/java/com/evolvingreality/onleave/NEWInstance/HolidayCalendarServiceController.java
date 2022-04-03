package com.evolvingreality.onleave.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class HolidayCalendarServiceController {

 private HolidayCalendarService holidaycalendarservice;


@GetMapping
("/get")
public Object get(@RequestParam(name = "Object") Object Object){
  return holidaycalendarservice.get(Object);
}


@GetMapping
("/getPage")
public Object getPage(@RequestParam(name = "Object") Object Object){
  return holidaycalendarservice.getPage(Object);
}


@GetMapping
("/save")
public Object save(@RequestParam(name = "Object") Object Object){
  return holidaycalendarservice.save(Object);
}


@GetMapping
("/delete")
public Object delete(@RequestParam(name = "Object") Object Object){
  return holidaycalendarservice.delete(Object);
}


}