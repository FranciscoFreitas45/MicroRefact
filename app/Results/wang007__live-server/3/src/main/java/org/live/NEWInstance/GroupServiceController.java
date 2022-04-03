package org.live.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class GroupServiceController {

 private GroupService groupservice;


@GetMapping
("/findAll")
public Object findAll(@RequestParam(name = "Object") Object Object){
  return groupservice.findAll(Object);
}


}