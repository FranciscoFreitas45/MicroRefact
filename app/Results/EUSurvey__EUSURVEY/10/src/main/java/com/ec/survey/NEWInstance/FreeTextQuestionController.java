package com.ec.survey.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class FreeTextQuestionController {

 private FreeTextQuestion freetextquestion;

 private FreeTextQuestion freetextquestion;


@PutMapping
("/setMinCharacters")
public void setMinCharacters(@RequestParam(name = "min") Integer min){
freetextquestion.setMinCharacters(min);
}


@PutMapping
("/setMaxCharacters")
public void setMaxCharacters(@RequestParam(name = "max") Integer max){
freetextquestion.setMaxCharacters(max);
}


@PutMapping
("/setNumRows")
public void setNumRows(@RequestParam(name = "num") Integer num){
freetextquestion.setNumRows(num);
}


@PutMapping
("/setIsPassword")
public void setIsPassword(@RequestParam(name = "isPassword") Boolean isPassword){
freetextquestion.setIsPassword(isPassword);
}


@PutMapping
("/setIsComparable")
public void setIsComparable(@RequestParam(name = "isComparable") Boolean isComparable){
freetextquestion.setIsComparable(isComparable);
}


}