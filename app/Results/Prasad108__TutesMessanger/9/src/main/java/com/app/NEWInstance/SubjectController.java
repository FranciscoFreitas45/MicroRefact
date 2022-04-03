package com.app.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class SubjectController {

 private Subject subject;

 private Subject subject;


@PutMapping
("/setInstitute")
public void setInstitute(@RequestParam(name = "institute") Institute institute){
subject.setInstitute(institute);
}


}