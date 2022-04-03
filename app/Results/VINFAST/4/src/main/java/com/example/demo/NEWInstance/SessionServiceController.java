package com.example.demo.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class SessionServiceController {

 private SessionService sessionservice;


@GetMapping
("/get")
public T get(@RequestParam(name = "name") String name,@RequestParam(name = "defaultValue") T defaultValue){
  return sessionservice.get(name,defaultValue);
}


@PutMapping
("/remove")
public void remove(@RequestParam(name = "name") String name){
sessionservice.remove(name);
}


@PutMapping
("/set")
public void set(@RequestParam(name = "name") String name,@RequestParam(name = "value") Object value){
sessionservice.set(name,value);
}


}