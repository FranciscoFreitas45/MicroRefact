package org.danyuan.application.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class SysCrawlerTaskErrInfoServiceController {

 private SysCrawlerTaskErrInfoService syscrawlertaskerrinfoservice;


@GetMapping
("/findOne")
public Object findOne(@RequestParam(name = "Object") Object Object){
  return syscrawlertaskerrinfoservice.findOne(Object);
}


}