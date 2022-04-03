package com.ec.survey.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class PossibleAnswerController {

 private PossibleAnswer possibleanswer;

 private PossibleAnswer possibleanswer;


@PutMapping
("/setQuestionId")
public void setQuestionId(@RequestParam(name = "questionId") int questionId){
possibleanswer.setQuestionId(questionId);
}


@PutMapping
("/clearForJSON")
public void clearForJSON(){
possibleanswer.clearForJSON();
}


@PutMapping
("/setEcfProfile")
public void setEcfProfile(@RequestParam(name = "ecfProfile") ECFProfile ecfProfile){
possibleanswer.setEcfProfile(ecfProfile);
}


}