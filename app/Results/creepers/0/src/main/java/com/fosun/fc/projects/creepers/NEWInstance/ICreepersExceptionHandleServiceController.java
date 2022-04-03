package com.fosun.fc.projects.creepers.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class ICreepersExceptionHandleServiceController {

 private ICreepersExceptionHandleService icreepersexceptionhandleservice;


@PutMapping
("/handleExceptionAndPrintLog")
public void handleExceptionAndPrintLog(@RequestParam(name = "param") CreepersLoginParamDTO param){
icreepersexceptionhandleservice.handleExceptionAndPrintLog(param);
}


}