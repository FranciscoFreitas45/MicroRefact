package com.webapp.NEW;
 import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.webapp.models.Result;
@RestController
@CrossOrigin
public class ResultTrainingSessionController {

@Autowired
 private ResultTrainingSessionService resulttrainingsessionservice;


@GetMapping
("/TrainingSession/{id}/Result/getResults")
public List<Result> getResults(@PathVariable(name="id") Long id){
return resulttrainingsessionservice.getResults(id);
}


@PutMapping
("/TrainingSession/{id}/Result/setResults")
public void setResults(@PathVariable(name="id") Long id,@RequestBody List<Result> results){
resulttrainingsessionservice.setResults(id,results);
}


}