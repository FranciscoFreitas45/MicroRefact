package com.app.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class ClassesServiceController {

 private ClassesService classesservice;


@GetMapping
("/getallOfParticularBranch")
public List<Classes> getallOfParticularBranch(@RequestParam(name = "branch") Branch branch){
  return classesservice.getallOfParticularBranch(branch);
}


@PutMapping
("/create")
public void create(@RequestParam(name = "classes") Classes classes){
classesservice.create(classes);
}


@PutMapping
("/update")
public void update(@RequestParam(name = "classes") Classes classes){
classesservice.update(classes);
}


@PutMapping
("/delet")
public void delet(@RequestParam(name = "id") int id){
classesservice.delet(id);
}


@GetMapping
("/find")
public Classes find(@RequestParam(name = "id") int id){
  return classesservice.find(id);
}


}