package com.qidian.hcm.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class CustomizedFieldServiceController {

 private CustomizedFieldService customizedfieldservice;


@GetMapping
("/getById")
public CustomizedField getById(@RequestParam(name = "id") Long id){
  return customizedfieldservice.getById(id);
}


}