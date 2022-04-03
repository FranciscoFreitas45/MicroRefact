package com.fosun.fc.projects.creepers.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class ICreepersListServiceController {

 private ICreepersListService icreeperslistservice;


@PutMapping
("/updateList")
public void updateList(@RequestParam(name = "param") CreepersLoginParamDTO param){
icreeperslistservice.updateList(param);
}


@GetMapping
("/addTaskByMerName")
public JsonResult addTaskByMerName(@RequestParam(name = "requestType") String requestType,@RequestParam(name = "merName") String merName){
  return icreeperslistservice.addTaskByMerName(requestType,merName);
}


}