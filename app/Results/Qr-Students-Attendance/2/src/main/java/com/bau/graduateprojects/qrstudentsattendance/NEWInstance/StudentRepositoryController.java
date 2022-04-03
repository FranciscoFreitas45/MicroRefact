package com.bau.graduateprojects.qrstudentsattendance.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class StudentRepositoryController {

 private StudentRepository studentrepository;


@GetMapping
("/existById")
public boolean existById(@RequestParam(name = "studentId") Long studentId){
  return studentrepository.existById(studentId);
}


@GetMapping
("/getById")
public StudentEntity getById(@RequestParam(name = "id") Long id){
  return studentrepository.getById(id);
}


}