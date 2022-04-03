package com.webapp.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class AnswerRepositoryController {

 private AnswerRepository answerrepository;


@GetMapping
("/existsById")
public Object existsById(@RequestParam(name = "Object") Object Object){
  return answerrepository.existsById(Object);
}


@GetMapping
("/delete")
public Object delete(@RequestParam(name = "Object") Object Object){
  return answerrepository.delete(Object);
}


}