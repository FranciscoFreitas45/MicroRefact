package com.ec.survey.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class QuestionController {

 private Question question;

 private Question question;


@PutMapping
("/setAttributeName")
public void setAttributeName(@RequestParam(name = "attributeName") String attributeName){
question.setAttributeName(attributeName);
}


@PutMapping
("/setIsAttribute")
public void setIsAttribute(@RequestParam(name = "attribute") Boolean attribute){
question.setIsAttribute(attribute);
}


@PutMapping
("/setHelp")
public void setHelp(@RequestParam(name = "help") String help){
question.setHelp(help);
}


@PutMapping
("/setEcfCompetency")
public void setEcfCompetency(@RequestParam(name = "ecfCompetency") ECFCompetency ecfCompetency){
question.setEcfCompetency(ecfCompetency);
}


}