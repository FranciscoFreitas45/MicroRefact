package com.ipe.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class ResourceServiceController {

 private ResourceService resourceservice;


@GetMapping
("/getResources")
public List<Resource> getResources(@RequestParam(name = "pid") String pid){
  return resourceservice.getResources(pid);
}


@GetMapping
("/findAll")
public Object findAll(@RequestParam(name = "Object") Object Object){
  return resourceservice.findAll(Object);
}


@GetMapping
("/get")
public Object get(@RequestParam(name = "Object") Object Object){
  return resourceservice.get(Object);
}


@GetMapping
("/save")
public Object save(@RequestParam(name = "Object") Object Object){
  return resourceservice.save(Object);
}


@GetMapping
("/saveResource")
public Resource saveResource(@RequestParam(name = "resource") Resource resource){
  return resourceservice.saveResource(resource);
}


@GetMapping
("/delete")
public Object delete(@RequestParam(name = "Object") Object Object){
  return resourceservice.delete(Object);
}


}