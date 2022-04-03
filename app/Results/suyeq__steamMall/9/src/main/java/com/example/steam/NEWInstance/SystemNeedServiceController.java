package com.example.steam.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class SystemNeedServiceController {

 private SystemNeedService systemneedservice;


@GetMapping
("/addSystemNeed")
public long addSystemNeed(@RequestParam(name = "systemNeed") SystemNeed systemNeed){
  return systemneedservice.addSystemNeed(systemNeed);
}


}