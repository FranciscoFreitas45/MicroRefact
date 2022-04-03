package com.hmm.NEW;
 import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.hmm.Work.entity.Bcard;
@RestController
@CrossOrigin
public class BcardEmployeeController {

@Autowired
 private BcardEmployeeService bcardemployeeservice;


@GetMapping
("/Employee/{id}/Bcard/getBcards")
public Set<Bcard> getBcards(@PathVariable(name="id") Integer emp_id){
return bcardemployeeservice.getBcards(emp_id);
}


@PutMapping
("/Employee/{id}/Bcard/setBcards")
public void setBcards(@PathVariable(name="id") Integer emp_id,@RequestBody Set<Bcard> bcards){
bcardemployeeservice.setBcards(emp_id,bcards);
}


}