package org.danyuan.application.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class SysShareFilePathInfoServiceController {

 private SysShareFilePathInfoService syssharefilepathinfoservice;


@GetMapping
("/save")
public Object save(@RequestParam(name = "Object") Object Object){
  return syssharefilepathinfoservice.save(Object);
}


@GetMapping
("/findOne")
public Object findOne(@RequestParam(name = "Object") Object Object){
  return syssharefilepathinfoservice.findOne(Object);
}


}