package com.dtdhehe.ptulife.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class PtuAnswerController {

 private PtuAnswerRepository ptuanswerrepository;


@PutMapping
("/setAnswerDate/{id}")
public void setAnswerDate(@PathVariable(name = "id") String id,@RequestParam(name = "answerDate") String answerDate){
 ptuanswerrepository.setAnswerDate(id,answerDate);
}


}