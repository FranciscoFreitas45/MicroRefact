package org.danyuan.application.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class SysCrawlerTaskSelfCodeInfoServiceController {

 private SysCrawlerTaskSelfCodeInfoService syscrawlertaskselfcodeinfoservice;


@GetMapping
("/findOne")
public Object findOne(@RequestParam(name = "Object") Object Object){
  return syscrawlertaskselfcodeinfoservice.findOne(Object);
}


}