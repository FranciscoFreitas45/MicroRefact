package com.ec.survey.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class DraftController {

 private Draft draft;

 private Draft draft;


@PutMapping
("/setAnswerSet")
public void setAnswerSet(@RequestParam(name = "s") AnswerSet s){
draft.setAnswerSet(s);
}


@PutMapping
("/setUniqueId")
public void setUniqueId(@RequestParam(name = "uniqueId") String uniqueId){
draft.setUniqueId(uniqueId);
}


}