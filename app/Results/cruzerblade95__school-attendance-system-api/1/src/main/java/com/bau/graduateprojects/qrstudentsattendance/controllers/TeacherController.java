package com.bau.graduateprojects.qrstudentsattendance.controllers;
 import com.bau.graduateprojects.qrstudentsattendance.entities.CourseEntity;
import com.bau.graduateprojects.qrstudentsattendance.entities.TeacherEntity;
import com.bau.graduateprojects.qrstudentsattendance.entities.TeacherCourseEntity;
import com.bau.graduateprojects.qrstudentsattendance.servicies.teacher.TeacherService;
import org.springframework.web.bind.annotation;
import java.util.List;
@RestController
@RequestMapping("/api/v1/teacher")
public class TeacherController {

 private  TeacherService teacherService;

public TeacherController(TeacherService teacherService) {
    this.teacherService = teacherService;
}
@DeleteMapping("/{id}")
public void removeById(Long id){
    teacherService.removeById(id);
}


@GetMapping("/id/{id}")
public TeacherEntity getById(Long id){
    return teacherService.getById(id);
}


@GetMapping("/id/{id}/courses")
public TeacherCourseEntity getByTeacherId(Long id){
    String query = "SELECT * FROM {id}";
    return teacherService.getByTeacherId(id);
}


@GetMapping("/username/{username}")
public TeacherEntity getByUsername(String username){
    return teacherService.getByUsername(username);
}


@PutMapping
public TeacherEntity update(TeacherEntity teacherEntity){
    return teacherService.update(teacherEntity);
}


@PostMapping
public TeacherEntity insert(TeacherEntity teacherEntity){
    return teacherService.insert(teacherEntity);
}


@GetMapping
public List<TeacherEntity> list(){
    return teacherService.list();
}


@PostMapping("/login/{username}/{password}")
public TeacherEntity login(String username,String password){
    return teacherService.login(username, password);
}


@GetMapping("/count")
public Long getCount(){
    return teacherService.getCount();
}


}