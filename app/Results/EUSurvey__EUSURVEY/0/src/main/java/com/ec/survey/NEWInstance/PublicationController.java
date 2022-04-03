package com.ec.survey.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class PublicationController {

 private Publication publication;

 private Publication publication;


@GetMapping
("/isAllQuestions")
public boolean isAllQuestions(){
  return publication.isAllQuestions();
}


@GetMapping
("/isSelected")
public boolean isSelected(@RequestParam(name = "questionId") String questionId){
  return publication.isSelected(questionId);
}


}