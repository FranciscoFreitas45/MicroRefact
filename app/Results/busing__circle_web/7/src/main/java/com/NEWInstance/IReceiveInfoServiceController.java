package com.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class IReceiveInfoServiceController {

 private IReceiveInfoService ireceiveinfoservice;


@GetMapping
("/isExistsReceiveInfo")
public boolean isExistsReceiveInfo(@RequestParam(name = "userId") int userId){
  return ireceiveinfoservice.isExistsReceiveInfo(userId);
}


@GetMapping
("/saveDefaultReceiveInfo")
public boolean saveDefaultReceiveInfo(@RequestParam(name = "user") User user){
  return ireceiveinfoservice.saveDefaultReceiveInfo(user);
}


@GetMapping
("/queryReceiveInfo")
public List<Map<String,Object>> queryReceiveInfo(@RequestParam(name = "userId") int userId){
  return ireceiveinfoservice.queryReceiveInfo(userId);
}


@GetMapping
("/queryPayAndShipInfo")
public Map<String,Object> queryPayAndShipInfo(@RequestParam(name = "userId") int userId){
  return ireceiveinfoservice.queryPayAndShipInfo(userId);
}


}