package com.cym.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class BasicServiceController {

 private BasicService basicservice;


@GetMapping
("/contain")
public boolean contain(@RequestParam(name = "content") String content){
  return basicservice.contain(content);
}


}