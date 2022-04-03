package com.webapp.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class WordRepositoryController {

 private WordRepository wordrepository;


@GetMapping
("/findById")
public Object findById(@RequestParam(name = "Object") Object Object){
  return wordrepository.findById(Object);
}


}