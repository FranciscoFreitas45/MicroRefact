package com.ushahidi.swiftriver.core.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class LinkDaoController {

 private LinkDao linkdao;


@GetMapping
("/findByHash")
public Link findByHash(@RequestParam(name = "hash") String hash){
  return linkdao.findByHash(hash);
}


@GetMapping
("/create")
public Object create(@RequestParam(name = "Object") Object Object){
  return linkdao.create(Object);
}


@GetMapping
("/findById")
public Object findById(@RequestParam(name = "Object") Object Object){
  return linkdao.findById(Object);
}


@PutMapping
("/getLinks")
public void getLinks(@RequestParam(name = "drops") List<Drop> drops){
linkdao.getLinks(drops);
}


}