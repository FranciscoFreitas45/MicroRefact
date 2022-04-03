package com.ec.survey.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class RatingQuestionController {

 private RatingQuestion ratingquestion;

 private RatingQuestion ratingquestion;


@GetMapping
("/containsChild")
public boolean containsChild(@RequestParam(name = "id") int id){
  return ratingquestion.containsChild(id);
}


}