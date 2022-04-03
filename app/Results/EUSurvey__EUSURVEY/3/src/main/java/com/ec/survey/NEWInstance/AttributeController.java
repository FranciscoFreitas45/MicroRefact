package com.ec.survey.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class AttributeController {

 private Attribute attribute;

 private Attribute attribute;


@PutMapping
("/setValue")
public void setValue(@RequestParam(name = "value") String value){
attribute.setValue(value);
}


}