package org.danyuan.application.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class SysDbmsTabsInfoServiceController {

 private SysDbmsTabsInfoService sysdbmstabsinfoservice;


@GetMapping
("/findOne")
public SysDbmsTabsInfo findOne(@RequestParam(name = "info") SysDbmsTabsInfo info){
  return sysdbmstabsinfoservice.findOne(info);
}


}