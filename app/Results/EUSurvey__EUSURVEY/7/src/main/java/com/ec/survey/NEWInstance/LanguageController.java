package com.ec.survey.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class LanguageController {

 private Language language;


@GetMapping
("/getName")
public String getName(){
  return language.getName();
}


@GetMapping
("/getCode")
public String getCode(){
  return language.getCode();
}


@GetMapping
("/equalsIgnoreCase")
public Object equalsIgnoreCase(@RequestParam(name = "Object") Object Object){
  return language.equalsIgnoreCase(Object);
}


@GetMapping
("/toLowerCase")
public Object toLowerCase(@RequestParam(name = "Object") Object Object){
  return language.toLowerCase(Object);
}


@GetMapping
("/isOfficial")
public boolean isOfficial(){
  return language.isOfficial();
}


}