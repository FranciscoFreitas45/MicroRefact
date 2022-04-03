package com.empl.mgr.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class TeTrainingController {

 private TeTraining tetraining;

 private TeTraining tetraining;


@PutMapping
("/setNumber")
public void setNumber(@RequestParam(name = "number") Integer number){
tetraining.setNumber(number);
}


}