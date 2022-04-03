package com.cym.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class OperateLogServiceController {

 private OperateLogService operatelogservice;


@PutMapping
("/addLog")
public void addLog(@RequestParam(name = "beforeConf") String beforeConf,@RequestParam(name = "afterConf") String afterConf,@RequestParam(name = "adminName") String adminName){
operatelogservice.addLog(beforeConf,afterConf,adminName);
}


}