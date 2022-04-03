package org.live.NEW;
 import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.live.school.entity.Grade;
@RestController
@CrossOrigin
public class GradeMemberController {

@Autowired
 private GradeMemberService gradememberservice;


@PutMapping
("/Member/{id}/Grade/setGrade")
public void setGrade(@PathVariable(name="id") String id2FF1,@RequestBody Grade grade){
gradememberservice.setGrade(id2FF1,grade);
}


@GetMapping
("/Member/{id}/Grade/getGrade")
public Grade getGrade(@PathVariable(name="id") String id2FF1){
return gradememberservice.getGrade(id2FF1);
}


}