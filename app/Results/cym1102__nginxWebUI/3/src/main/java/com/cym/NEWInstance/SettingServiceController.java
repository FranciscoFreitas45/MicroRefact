package com.cym.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class SettingServiceController {

 private SettingService settingservice;


@GetMapping
("/get")
public String get(@RequestParam(name = "key") String key){
  return settingservice.get(key);
}


@PutMapping
("/remove")
public void remove(@RequestParam(name = "key") String key){
settingservice.remove(key);
}


@PutMapping
("/set")
public void set(@RequestParam(name = "key") String key,@RequestParam(name = "value") String value){
settingservice.set(key,value);
}


@GetMapping
("/equals")
public Object equals(@RequestParam(name = "Object") Object Object){
  return settingservice.equals(Object);
}


}