package org.danyuan.application.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class SysDbmsUserIndexInfoServiceController {

 private SysDbmsUserIndexInfoService sysdbmsuserindexinfoservice;


@GetMapping
("/findAll")
public List<SysDbmsUserIndexInfo> findAll(){
  return sysdbmsuserindexinfoservice.findAll();
}


}