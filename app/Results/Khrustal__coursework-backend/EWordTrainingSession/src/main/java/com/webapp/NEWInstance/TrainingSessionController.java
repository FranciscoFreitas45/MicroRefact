package com.webapp.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class TrainingSessionController {

 private TrainingSessionRepository trainingsessionrepository;


@GetMapping
("/containsWord/{id}")
public boolean containsWord(@PathVariable(name = "id") Long id,@RequestParam(name = "wordId") Long wordId){
 return trainingsessionrepository.containsWord(id,wordId);
}


@PutMapping
("/removeWord/{id}")
public void removeWord(@PathVariable(name = "id") Long id,@RequestParam(name = "wordId") Long wordId){
 trainingsessionrepository.removeWord(id,wordId);
}


}