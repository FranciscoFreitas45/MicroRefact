package com.bau.graduateprojects.qrstudentsattendance.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class TeacherCourseRepositoryController {

 private TeacherCourseRepository teachercourserepository;


@GetMapping
("/exist")
public boolean exist(@RequestParam(name = "teacherId") Long teacherId,@RequestParam(name = "courseId") Long courseId){
  return teachercourserepository.exist(teacherId,courseId);
}


@GetMapping
("/insert")
public TeacherCourseEntity insert(@RequestParam(name = "entity") TeacherCourseEntity entity){
  return teachercourserepository.insert(entity);
}


@GetMapping
("/getByTeacherIdAndCourseId")
public TeacherCourseEntity getByTeacherIdAndCourseId(@RequestParam(name = "tId") Long tId,@RequestParam(name = "cId") Long cId){
  return teachercourserepository.getByTeacherIdAndCourseId(tId,cId);
}


@PutMapping
("/removeById")
public void removeById(@RequestParam(name = "id") Long id){
teachercourserepository.removeById(id);
}


@GetMapping
("/getAllCoursesByTeacherId")
public List<TeacherCourseEntity> getAllCoursesByTeacherId(@RequestParam(name = "tId") Long tId){
  return teachercourserepository.getAllCoursesByTeacherId(tId);
}


}