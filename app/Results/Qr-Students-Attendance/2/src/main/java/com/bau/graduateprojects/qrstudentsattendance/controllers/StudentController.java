package com.bau.graduateprojects.qrstudentsattendance.controllers;
 import com.bau.graduateprojects.qrstudentsattendance.entities.StudentEntity;
import com.bau.graduateprojects.qrstudentsattendance.servicies.student.StudentService;
import org.springframework.web.bind.annotation;
import java.util.List;
@RestController
@RequestMapping("/api/v1/student")
public class StudentController {

 private  StudentService studentService;

public StudentController(StudentService studentService) {
    this.studentService = studentService;
}
@DeleteMapping("/{id}")
public void removeById(Long id){
    studentService.removeById(id);
}


@GetMapping("/id/{id}")
public StudentEntity getById(Long id){
    return studentService.getById(id);
}


@GetMapping("/username/{username}")
public StudentEntity getByUsername(String username){
    return studentService.getByUsername(username);
}


@PostMapping
public StudentEntity insert(StudentEntity studentEntity){
    return studentService.insert(studentEntity);
}


@PutMapping()
public StudentEntity update(StudentEntity studentEntity){
    return studentService.update(studentEntity);
}


@GetMapping
public List<StudentEntity> list(){
    return studentService.list();
}


@GetMapping("/count")
public Long getCount(){
    return studentService.getCount();
}


}