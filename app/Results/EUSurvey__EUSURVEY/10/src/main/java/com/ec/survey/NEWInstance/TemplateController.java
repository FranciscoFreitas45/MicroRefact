package com.ec.survey.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class TemplateController {

 private Template template;

 private Template template;


@PutMapping
("/setOwner")
public void setOwner(@RequestParam(name = "owner") User owner){
template.setOwner(owner);
}


@PutMapping
("/setElement")
public void setElement(@RequestParam(name = "element") Element element){
template.setElement(element);
}


}