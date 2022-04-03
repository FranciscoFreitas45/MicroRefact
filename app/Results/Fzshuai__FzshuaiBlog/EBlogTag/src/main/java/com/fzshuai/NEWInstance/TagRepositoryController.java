package com.fzshuai.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class TagRepositoryController {

 private TagRepository tagrepository;


@GetMapping
("/save")
public Object save(@RequestParam(name = "Object") Object Object){
  return tagrepository.save(Object);
}


@GetMapping
("/findById")
public Object findById(@RequestParam(name = "Object") Object Object){
  return tagrepository.findById(Object);
}


@GetMapping
("/findByName")
public Tag findByName(@RequestParam(name = "name") String name){
  return tagrepository.findByName(name);
}


@GetMapping
("/findAll")
public Object findAll(@RequestParam(name = "Object") Object Object){
  return tagrepository.findAll(Object);
}


@GetMapping
("/findTop")
public List<Tag> findTop(@RequestParam(name = "pageable") Pageable pageable){
  return tagrepository.findTop(pageable);
}


@GetMapping
("/findAllById")
public Object findAllById(@RequestParam(name = "Object") Object Object){
  return tagrepository.findAllById(Object);
}


@GetMapping
("/deleteById")
public Object deleteById(@RequestParam(name = "Object") Object Object){
  return tagrepository.deleteById(Object);
}


}