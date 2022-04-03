package com.sobey.cmop.mvc.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class SearchFilterController {

 private SearchFilter searchfilter;

 private SearchFilter searchfilter;


@GetMapping
("/parse")
public Map<String,SearchFilter> parse(@RequestParam(name = "searchParams") Map<String,Object> searchParams){
  return searchfilter.parse(searchParams);
}


}