package com.app.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class SubjectDivCompositServiceController {

 private SubjectDivCompositService subjectdivcompositservice;


@PutMapping
("/deleteByDivId")
public void deleteByDivId(@RequestParam(name = "subId") int subId,@RequestParam(name = "divId") int divId){
subjectdivcompositservice.deleteByDivId(subId,divId);
}


@GetMapping
("/findByDivId")
public List<Subject> findByDivId(@RequestParam(name = "divId") int divId){
  return subjectdivcompositservice.findByDivId(divId);
}


@PutMapping
("/create")
public void create(@RequestParam(name = "subDivComp") SubjectDivComposit subDivComp){
subjectdivcompositservice.create(subDivComp);
}


@GetMapping
("/findSubjectName")
public String findSubjectName(@RequestParam(name = "subDivCompId") int subDivCompId){
  return subjectdivcompositservice.findSubjectName(subDivCompId);
}


}