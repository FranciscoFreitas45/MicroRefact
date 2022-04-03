package com.app.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class ParentServiceController {

 private ParentService parentservice;


@GetMapping
("/findByStudentId")
public Parent findByStudentId(@RequestParam(name = "studId") int studId){
  return parentservice.findByStudentId(studId);
}


@PutMapping
("/create")
public void create(@RequestParam(name = "parent") Parent parent){
parentservice.create(parent);
}


}