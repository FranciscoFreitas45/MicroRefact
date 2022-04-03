package com.lingxiang2014.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class LogServiceController {

 private LogService logservice;


@GetMapping
("/findPage")
public Object findPage(@RequestParam(name = "Object") Object Object){
  return logservice.findPage(Object);
}


@GetMapping
("/find")
public Object find(@RequestParam(name = "Object") Object Object){
  return logservice.find(Object);
}


@GetMapping
("/delete")
public Object delete(@RequestParam(name = "Object") Object Object){
  return logservice.delete(Object);
}


@PutMapping
("/clear")
public void clear(){
logservice.clear();
}


}