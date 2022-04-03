package com.ipe.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class ResourceRepositoryController {

 private ResourceRepository resourcerepository;


@GetMapping
("/findOne")
public Object findOne(@RequestParam(name = "Object") Object Object){
  return resourcerepository.findOne(Object);
}


}