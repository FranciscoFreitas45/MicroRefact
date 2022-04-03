package com.ec.survey.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class AttributeNameController {

 private AttributeName attributename;

 private AttributeName attributename;


@PutMapping
("/setOwnerId")
public void setOwnerId(@RequestParam(name = "ownerId") Integer ownerId){
attributename.setOwnerId(ownerId);
}


}