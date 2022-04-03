package com.cym.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class TemplateServiceController {

 private TemplateService templateservice;


@GetMapping
("/getParamList")
public List<Param> getParamList(@RequestParam(name = "templateId") String templateId){
  return templateservice.getParamList(templateId);
}


}