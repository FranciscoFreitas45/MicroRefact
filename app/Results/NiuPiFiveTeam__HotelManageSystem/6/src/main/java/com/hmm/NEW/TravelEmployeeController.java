package com.hmm.NEW;
 import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.hmm.travel.entity.Travel;
@RestController
@CrossOrigin
public class TravelEmployeeController {

@Autowired
 private TravelEmployeeService travelemployeeservice;


@GetMapping
("/Employee/{id}/Travel/getTravels")
public Set<Travel> getTravels(@PathVariable(name="id") Integer emp_id){
return travelemployeeservice.getTravels(emp_id);
}


@PutMapping
("/Employee/{id}/Travel/setTravels")
public void setTravels(@PathVariable(name="id") Integer emp_id,@RequestBody Set<Travel> travels){
travelemployeeservice.setTravels(emp_id,travels);
}


}