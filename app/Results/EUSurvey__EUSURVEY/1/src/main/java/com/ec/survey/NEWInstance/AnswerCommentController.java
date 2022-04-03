package com.ec.survey.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class AnswerCommentController {

 private AnswerComment answercomment;

 private AnswerComment answercomment;


@PutMapping
("/setQuestionUid")
public void setQuestionUid(@RequestParam(name = "questionUid") String questionUid){
answercomment.setQuestionUid(questionUid);
}


@PutMapping
("/setUniqueCode")
public void setUniqueCode(@RequestParam(name = "uniqueCode") String uniqueCode){
answercomment.setUniqueCode(uniqueCode);
}


@PutMapping
("/setText")
public void setText(@RequestParam(name = "text") String text){
answercomment.setText(text);
}


@PutMapping
("/setDate")
public void setDate(@RequestParam(name = "date") Date date){
answercomment.setDate(date);
}


@PutMapping
("/setParent")
public void setParent(@RequestParam(name = "parent") AnswerComment parent){
answercomment.setParent(parent);
}


}