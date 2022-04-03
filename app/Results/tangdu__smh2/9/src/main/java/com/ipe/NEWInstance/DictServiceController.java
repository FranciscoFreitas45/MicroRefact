package com.ipe.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class DictServiceController {

 private DictService dictservice;


@GetMapping
("/findEntity")
public Object findEntity(@RequestParam(name = "Object") Object Object){
  return dictservice.findEntity(Object);
}


@GetMapping
("/save")
public Object save(@RequestParam(name = "Object") Object Object){
  return dictservice.save(Object);
}


@GetMapping
("/delete")
public Object delete(@RequestParam(name = "Object") Object Object){
  return dictservice.delete(Object);
}


}