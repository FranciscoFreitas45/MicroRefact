package com.lingxiang2014.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class StaticServiceController {

 private StaticService staticservice;


@GetMapping
("/build")
public int build(@RequestParam(name = "article") Article article){
  return staticservice.build(article);
}


@GetMapping
("/delete")
public int delete(@RequestParam(name = "article") Article article){
  return staticservice.delete(article);
}


@GetMapping
("/buildOther")
public int buildOther(){
  return staticservice.buildOther();
}


@GetMapping
("/buildIndex")
public int buildIndex(){
  return staticservice.buildIndex();
}


}