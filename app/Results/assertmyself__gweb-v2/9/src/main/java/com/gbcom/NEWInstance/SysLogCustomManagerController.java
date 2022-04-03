package com.gbcom.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class SysLogCustomManagerController {

 private SysLogCustomManager syslogcustommanager;


@PutMapping
("/log")
public void log(@RequestParam(name = "request") HttpServletRequest request,@RequestParam(name = "logType") String logType){
syslogcustommanager.log(request,logType);
}


}