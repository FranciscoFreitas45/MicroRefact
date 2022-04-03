package com.webapp.NEW;
 import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.webapp.models.TrainingSession;
@RestController
@CrossOrigin
public class TrainingSessionResultController {

@Autowired
 private TrainingSessionResultService trainingsessionresultservice;


@PutMapping
("/Result/{id}/TrainingSession/setSession")
public void setSession(@PathVariable(name="id") Long idOXI5,@RequestBody TrainingSession session){
trainingsessionresultservice.setSession(idOXI5,session);
}


}