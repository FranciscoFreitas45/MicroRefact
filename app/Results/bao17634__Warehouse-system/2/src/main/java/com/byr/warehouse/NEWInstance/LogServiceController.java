package com.byr.warehouse.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class LogServiceController {

 private LogService logservice;


@PutMapping
("/saveOpLog")
public void saveOpLog(@RequestParam(name = "username") String username,@RequestParam(name = "operation") String operation,@RequestParam(name = "result") String result,@RequestParam(name = "detail") String detail){
logservice.saveOpLog(username,operation,result,detail);
}


@PutMapping
("/saveSysLog")
public void saveSysLog(@RequestParam(name = "logMessage") String logMessage){
logservice.saveSysLog(logMessage);
}


}