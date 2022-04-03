package com.fzshuai.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class TagServiceController {

 private TagService tagservice;


@GetMapping
("/listTag")
public List<Tag> listTag(@RequestParam(name = "ids") String ids){
  return tagservice.listTag(ids);
}


}