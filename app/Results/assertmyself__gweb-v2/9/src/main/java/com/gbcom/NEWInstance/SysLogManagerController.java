package com.gbcom.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class SysLogManagerController {

 private SysLogManager syslogmanager;


@PutMapping
("/deleteLog")
public void deleteLog(@RequestParam(name = "userId") Long userId){
syslogmanager.deleteLog(userId);
}


@GetMapping
("/getTodayLoginCount")
public List<SysLog> getTodayLoginCount(@RequestParam(name = "time") String time){
  return syslogmanager.getTodayLoginCount(time);
}


@PutMapping
("/log")
public void log(@RequestParam(name = "username") String username,@RequestParam(name = "pageUrl") String pageUrl,@RequestParam(name = "userIp") String userIp,@RequestParam(name = "sessionId") String sessionId){
syslogmanager.log(username,pageUrl,userIp,sessionId);
}


}