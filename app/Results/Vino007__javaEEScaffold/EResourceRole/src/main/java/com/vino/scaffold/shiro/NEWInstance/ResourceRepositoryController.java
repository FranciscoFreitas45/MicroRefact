package com.vino.scaffold.shiro.NEWInstance;
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


@GetMapping
("/findByName")
public Resource findByName(@RequestParam(name = "name") String name){
  return resourcerepository.findByName(name);
}


@GetMapping
("/save")
public Object save(@RequestParam(name = "Object") Object Object){
  return resourcerepository.save(Object);
}


@GetMapping
("/findAll")
public Object findAll(@RequestParam(name = "Object") Object Object){
  return resourcerepository.findAll(Object);
}


@PutMapping
("/deleteAssociateById")
public void deleteAssociateById(@RequestParam(name = "resourceIds") Long resourceIds){
resourcerepository.deleteAssociateById(resourceIds);
}


}