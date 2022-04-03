package com.hmm.NEW;
 import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.hmm.calendars.entity.SchedulEvent;
@RestController
@CrossOrigin
public class SchedulEventEmployeeController {

@Autowired
 private SchedulEventEmployeeService scheduleventemployeeservice;


@GetMapping
("/Employee/{id}/SchedulEvent/getSchedulEventlist")
public List<SchedulEvent> getSchedulEventlist(@PathVariable(name="id") Integer emp_id){
return scheduleventemployeeservice.getSchedulEventlist(emp_id);
}


@PutMapping
("/Employee/{id}/SchedulEvent/setSchedulEventlist")
public void setSchedulEventlist(@PathVariable(name="id") Integer emp_id,@RequestBody List<SchedulEvent> schedulEventlist){
scheduleventemployeeservice.setSchedulEventlist(emp_id,schedulEventlist);
}


}