package com.easyshopping.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class TemplateServiceController {

 private TemplateService templateservice;


@GetMapping
("/get")
public Template get(@RequestParam(name = "id") String id){
  return templateservice.get(id);
}


}