package com.app.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class TeacherController {

 private Teacher teacher;

 private Teacher teacher;


@PutMapping
("/setPermissions")
public void setPermissions(@RequestParam(name = "permissions") Permissions permissions){
teacher.setPermissions(permissions);
}


@PutMapping
("/setFname")
public void setFname(@RequestParam(name = "fname") String fname){
teacher.setFname(fname);
}


@PutMapping
("/setContactno")
public void setContactno(@RequestParam(name = "contactno") String contactno){
teacher.setContactno(contactno);
}


@PutMapping
("/setInstitute")
public void setInstitute(@RequestParam(name = "institute") Institute institute){
teacher.setInstitute(institute);
}


@PutMapping
("/setLogin")
public void setLogin(@RequestParam(name = "login") Login login){
teacher.setLogin(login);
}


}