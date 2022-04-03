package com.ushahidi.swiftriver.core.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class DropDocumentRepositoryController {

 private DropDocumentRepository dropdocumentrepository;


@GetMapping
("/findInRiver")
public Object findInRiver(@RequestParam(name = "Object") Object Object){
  return dropdocumentrepository.findInRiver(Object);
}


@GetMapping
("/findInBucket")
public Object findInBucket(@RequestParam(name = "Object") Object Object){
  return dropdocumentrepository.findInBucket(Object);
}


@GetMapping
("/save")
public Object save(@RequestParam(name = "Object") Object Object){
  return dropdocumentrepository.save(Object);
}


@GetMapping
("/delete")
public Object delete(@RequestParam(name = "Object") Object Object){
  return dropdocumentrepository.delete(Object);
}


@GetMapping
("/findAll")
public List<DropDocument> findAll(@RequestParam(name = "ids") List<String> ids){
  return dropdocumentrepository.findAll(ids);
}


@GetMapping
("/find")
public Object find(@RequestParam(name = "Object") Object Object){
  return dropdocumentrepository.find(Object);
}


@GetMapping
("/deleteAll")
public Object deleteAll(@RequestParam(name = "Object") Object Object){
  return dropdocumentrepository.deleteAll(Object);
}


}