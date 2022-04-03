package com.hmm.NEW;
 import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.hmm.Work.entity.Work;
@RestController
@CrossOrigin
public class WorkEmployeeController {

@Autowired
 private WorkEmployeeService workemployeeservice;


@GetMapping
("/Employee/{id}/Work/getWorks")
public Set<Work> getWorks(@PathVariable(name="id") Integer emp_id){
return workemployeeservice.getWorks(emp_id);
}


@PutMapping
("/Employee/{id}/Work/setWorks")
public void setWorks(@PathVariable(name="id") Integer emp_id,@RequestBody Set<Work> works){
workemployeeservice.setWorks(emp_id,works);
}


}