package com.ushahidi.swiftriver.core.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class TagDaoController {

 private TagDao tagdao;


@GetMapping
("/findByHash")
public Tag findByHash(@RequestParam(name = "hash") String hash){
  return tagdao.findByHash(hash);
}


@GetMapping
("/create")
public Object create(@RequestParam(name = "Object") Object Object){
  return tagdao.create(Object);
}


@GetMapping
("/findById")
public Object findById(@RequestParam(name = "Object") Object Object){
  return tagdao.findById(Object);
}


@PutMapping
("/getTags")
public void getTags(@RequestParam(name = "drops") List<Drop> drops){
tagdao.getTags(drops);
}


}