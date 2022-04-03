package com.bau.graduateprojects.qrstudentsattendance.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class TeacherRepositoryController {

 private TeacherRepository teacherrepository;


@GetMapping
("/existById")
public boolean existById(@RequestParam(name = "teacherId") Long teacherId){
  return teacherrepository.existById(teacherId);
}


@GetMapping
("/getById")
public TeacherEntity getById(@RequestParam(name = "id") Long id){
  return teacherrepository.getById(id);
}


}