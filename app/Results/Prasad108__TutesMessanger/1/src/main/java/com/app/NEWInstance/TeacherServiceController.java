package com.app.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class TeacherServiceController {

 private TeacherService teacherservice;


@GetMapping
("/GetInstitute")
public Institute GetInstitute(@RequestParam(name = "id") int id){
  return teacherservice.GetInstitute(id);
}


@GetMapping
("/find")
public Teacher find(@RequestParam(name = "id") int id){
  return teacherservice.find(id);
}


@PutMapping
("/update")
public void update(@RequestParam(name = "teacher") Teacher teacher){
teacherservice.update(teacher);
}


@PutMapping
("/changeUserName")
public void changeUserName(@RequestParam(name = "newUserName") String newUserName,@RequestParam(name = "login") Login login){
teacherservice.changeUserName(newUserName,login);
}


@PutMapping
("/changePassword")
public void changePassword(@RequestParam(name = "newPassword") String newPassword,@RequestParam(name = "login") Login login){
teacherservice.changePassword(newPassword,login);
}


@PutMapping
("/create")
public void create(@RequestParam(name = "teacher") Teacher teacher){
teacherservice.create(teacher);
}


}