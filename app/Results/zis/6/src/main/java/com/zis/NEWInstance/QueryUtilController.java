package com.zis.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class QueryUtilController {

 private QueryUtil queryutil;

 private QueryUtil queryutil;


@GetMapping
("/eq")
public QueryUtil<T> eq(@RequestParam(name = "name") String name,@RequestParam(name = "value") Object value){
  return queryutil.eq(name,value);
}


@GetMapping
("/in")
public QueryUtil<T> in(@RequestParam(name = "name") String name,@RequestParam(name = "value") Object value){
  return queryutil.in(name,value);
}


@GetMapping
("/ne")
public QueryUtil<T> ne(@RequestParam(name = "name") String name,@RequestParam(name = "value") Object value){
  return queryutil.ne(name,value);
}


@GetMapping
("/desc")
public QueryUtil<T> desc(@RequestParam(name = "name") String name){
  return queryutil.desc(name);
}


@GetMapping
("/like")
public QueryUtil<T> like(@RequestParam(name = "name") String name,@RequestParam(name = "value") Object value){
  return queryutil.like(name,value);
}


@GetMapping
("/asc")
public QueryUtil<T> asc(@RequestParam(name = "name") String name){
  return queryutil.asc(name);
}


}