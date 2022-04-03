package com.fosun.fc.projects.creepers.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class ICreepersErrorListServiceController {

 private ICreepersErrorListService icreeperserrorlistservice;


@PutMapping
("/saveError")
public void saveError(@RequestParam(name = "merName") String merName,@RequestParam(name = "errorDesc") String errorDesc,@RequestParam(name = "taskType") String taskType){
icreeperserrorlistservice.saveError(merName,errorDesc,taskType);
}


}