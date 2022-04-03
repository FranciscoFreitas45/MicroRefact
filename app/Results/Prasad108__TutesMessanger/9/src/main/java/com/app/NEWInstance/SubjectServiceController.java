package com.app.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class SubjectServiceController {

 private SubjectService subjectservice;


@GetMapping
("/find")
public Subject find(@RequestParam(name = "id") int id){
  return subjectservice.find(id);
}


@GetMapping
("/getallOfInstitute")
public List<Subject> getallOfInstitute(@RequestParam(name = "instituteId") int instituteId){
  return subjectservice.getallOfInstitute(instituteId);
}


@PutMapping
("/deleteFromInstitute")
public void deleteFromInstitute(@RequestParam(name = "subId") int subId){
subjectservice.deleteFromInstitute(subId);
}


@PutMapping
("/create")
public void create(@RequestParam(name = "subject") Subject subject){
subjectservice.create(subject);
}


@PutMapping
("/update")
public void update(@RequestParam(name = "subject") Subject subject){
subjectservice.update(subject);
}


}