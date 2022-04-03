package com.bau.graduateprojects.qrstudentsattendance.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class StudentCourseRepositoryController {

 private StudentCourseRepository studentcourserepository;


@GetMapping
("/exist")
public boolean exist(@RequestParam(name = "studentId") Long studentId,@RequestParam(name = "courseId") Long courseId){
  return studentcourserepository.exist(studentId,courseId);
}


@GetMapping
("/insert")
public StudentCourseEntity insert(@RequestParam(name = "entity") StudentCourseEntity entity){
  return studentcourserepository.insert(entity);
}


@GetMapping
("/getByStudentIdAndCourseId")
public StudentCourseEntity getByStudentIdAndCourseId(@RequestParam(name = "sId") Long sId,@RequestParam(name = "cId") Long cId){
  return studentcourserepository.getByStudentIdAndCourseId(sId,cId);
}


@PutMapping
("/removeById")
public void removeById(@RequestParam(name = "id") Long id){
studentcourserepository.removeById(id);
}


@GetMapping
("/getAllCoursesByStudentId")
public List<StudentCourseEntity> getAllCoursesByStudentId(@RequestParam(name = "sId") Long sId){
  return studentcourserepository.getAllCoursesByStudentId(sId);
}


@GetMapping
("/getAllStudentsByCourseId")
public List<StudentCourseEntity> getAllStudentsByCourseId(@RequestParam(name = "cId") Long cId){
  return studentcourserepository.getAllStudentsByCourseId(cId);
}


}