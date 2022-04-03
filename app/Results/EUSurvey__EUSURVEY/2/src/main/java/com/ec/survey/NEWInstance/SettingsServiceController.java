package com.ec.survey.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class SettingsServiceController {

 private SettingsService settingsservice;


@GetMapping
("/get")
public String get(@RequestParam(name = "key") String key){
  return settingsservice.get(key);
}


}