package com.ec.survey.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class ChoiceQuestionController {

 private ChoiceQuestion choicequestion;

 private ChoiceQuestion choicequestion;


@PutMapping
("/setForeditor")
public void setForeditor(@RequestParam(name = "foreditor") boolean foreditor){
choicequestion.setForeditor(foreditor);
}


}