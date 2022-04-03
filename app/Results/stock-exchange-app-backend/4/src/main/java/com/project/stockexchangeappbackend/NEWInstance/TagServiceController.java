package com.project.stockexchangeappbackend.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class TagServiceController {

 private TagService tagservice;


@GetMapping
("/getTag")
public Optional<Tag> getTag(@RequestParam(name = "name") String name){
  return tagservice.getTag(name);
}


}