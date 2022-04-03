package com.gbcom.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class SysLogServiceController {

 private SysLogService syslogservice;


@GetMapping
("/findByPage")
public Object findByPage(@RequestParam(name = "Object") Object Object){
  return syslogservice.findByPage(Object);
}


@GetMapping
("/get")
public Object get(@RequestParam(name = "Object") Object Object){
  return syslogservice.get(Object);
}


@GetMapping
("/delete")
public Object delete(@RequestParam(name = "Object") Object Object){
  return syslogservice.delete(Object);
}


@GetMapping
("/findBySql")
public Object findBySql(@RequestParam(name = "Object") Object Object){
  return syslogservice.findBySql(Object);
}


}