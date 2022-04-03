package com.bau.graduateprojects.qrstudentsattendance.controllers;
 import com.bau.graduateprojects.qrstudentsattendance.entities.CourseEntity;
import com.bau.graduateprojects.qrstudentsattendance.servicies.course.CourseService;
import org.springframework.web.bind.annotation;
import java.util.List;
@RestController
@RequestMapping("/api/v1/course")
public class CourseController {

 private  CourseService courseService;

public CourseController(CourseService courseService) {
    this.courseService = courseService;
}
@DeleteMapping("/{id}")
public void removeById(Long id){
    courseService.removeById(id);
}


@GetMapping("/{id}")
public CourseEntity getById(Long id){
    return courseService.getById(id);
}


@PostMapping
public CourseEntity insert(CourseEntity courseEntity){
    return courseService.insert(courseEntity);
}


@PutMapping
public CourseEntity update(CourseEntity courseEntity){
    return courseService.update(courseEntity);
}


@GetMapping
public List<CourseEntity> list(){
    return courseService.list();
}


@GetMapping("/count")
public Long getCount(){
    return courseService.getCount();
}


}