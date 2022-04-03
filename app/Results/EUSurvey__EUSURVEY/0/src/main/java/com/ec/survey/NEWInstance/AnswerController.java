package com.ec.survey.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class AnswerController {

 private Answer answer;

 private Answer answer;


@PutMapping
("/setQuestionId")
public void setQuestionId(@RequestParam(name = "questionId") Integer questionId){
answer.setQuestionId(questionId);
}


@PutMapping
("/setQuestionUniqueId")
public void setQuestionUniqueId(@RequestParam(name = "questionUniqueId") String questionUniqueId){
answer.setQuestionUniqueId(questionUniqueId);
}


@PutMapping
("/setValue")
public void setValue(@RequestParam(name = "value") String value){
answer.setValue(value);
}


@PutMapping
("/setColumn")
public void setColumn(@RequestParam(name = "column") Integer column){
answer.setColumn(column);
}


@PutMapping
("/setId")
public void setId(@RequestParam(name = "id") Integer id){
answer.setId(id);
}


@PutMapping
("/setRow")
public void setRow(@RequestParam(name = "row") Integer row){
answer.setRow(row);
}


@PutMapping
("/setPossibleAnswerId")
public void setPossibleAnswerId(@RequestParam(name = "possibleAnswerId") Integer possibleAnswerId){
answer.setPossibleAnswerId(possibleAnswerId);
}


@PutMapping
("/setPossibleAnswerUniqueId")
public void setPossibleAnswerUniqueId(@RequestParam(name = "possibleAnswerUniqueId") String possibleAnswerUniqueId){
answer.setPossibleAnswerUniqueId(possibleAnswerUniqueId);
}


@PutMapping
("/setAnswerSet")
public void setAnswerSet(@RequestParam(name = "s") AnswerSet s){
answer.setAnswerSet(s);
}


}