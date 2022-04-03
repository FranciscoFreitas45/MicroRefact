package com.app.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class StudentController {

 private Student student;

 private Student student;


@PutMapping
("/setInstitute")
public void setInstitute(@RequestParam(name = "institute") Institute institute){
student.setInstitute(institute);
}


}