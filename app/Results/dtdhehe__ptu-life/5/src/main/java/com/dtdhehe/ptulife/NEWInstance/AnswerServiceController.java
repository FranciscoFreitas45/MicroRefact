package com.dtdhehe.ptulife.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class AnswerServiceController {

 private AnswerService answerservice;


@GetMapping
("/queryAnswerByUserId")
public Page<PtuAnswer> queryAnswerByUserId(@RequestParam(name = "userId") String userId,@RequestParam(name = "answerTitle") String answerTitle,@RequestParam(name = "pageable") Pageable pageable){
  return answerservice.queryAnswerByUserId(userId,answerTitle,pageable);
}


@PutMapping
("/delAnswerById")
public void delAnswerById(@RequestParam(name = "answerId") String answerId){
answerservice.delAnswerById(answerId);
}


@GetMapping
("/queryAllAnswer")
public List<PtuAnswer> queryAllAnswer(){
  return answerservice.queryAllAnswer();
}


}