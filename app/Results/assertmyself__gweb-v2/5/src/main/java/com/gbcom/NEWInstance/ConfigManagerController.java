package com.gbcom.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class ConfigManagerController {

 private ConfigManager configmanager;


@GetMapping
("/getSiteName")
public String getSiteName(){
  return configmanager.getSiteName();
}


@GetMapping
("/replace")
public Object replace(@RequestParam(name = "Object") Object Object){
  return configmanager.replace(Object);
}


}