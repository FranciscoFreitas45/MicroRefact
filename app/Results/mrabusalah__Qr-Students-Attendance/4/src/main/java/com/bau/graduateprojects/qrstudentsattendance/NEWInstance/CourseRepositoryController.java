package com.bau.graduateprojects.qrstudentsattendance.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class CourseRepositoryController {

 private CourseRepository courserepository;


@GetMapping
("/existById")
public boolean existById(@RequestParam(name = "courseId") Long courseId){
  return courserepository.existById(courseId);
}


@GetMapping
("/getById")
public CourseEntity getById(@RequestParam(name = "id") Long id){
  return courserepository.getById(id);
}


@GetMapping
("/update")
public CourseEntity update(@RequestParam(name = "courseEntity") CourseEntity courseEntity){
  return courserepository.update(courseEntity);
}


}