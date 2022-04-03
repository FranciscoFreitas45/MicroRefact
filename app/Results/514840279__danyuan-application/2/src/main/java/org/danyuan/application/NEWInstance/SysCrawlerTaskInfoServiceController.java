package org.danyuan.application.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class SysCrawlerTaskInfoServiceController {

 private SysCrawlerTaskInfoService syscrawlertaskinfoservice;


@GetMapping
("/findOne")
public Object findOne(@RequestParam(name = "Object") Object Object){
  return syscrawlertaskinfoservice.findOne(Object);
}


}