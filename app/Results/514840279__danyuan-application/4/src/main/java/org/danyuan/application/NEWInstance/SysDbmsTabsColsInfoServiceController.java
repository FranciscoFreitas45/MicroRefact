package org.danyuan.application.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class SysDbmsTabsColsInfoServiceController {

 private SysDbmsTabsColsInfoService sysdbmstabscolsinfoservice;


@GetMapping
("/findAll")
public List<SysDbmsTabsColsInfo> findAll(@RequestParam(name = "info") SysDbmsTabsColsInfo info){
  return sysdbmstabscolsinfoservice.findAll(info);
}


}