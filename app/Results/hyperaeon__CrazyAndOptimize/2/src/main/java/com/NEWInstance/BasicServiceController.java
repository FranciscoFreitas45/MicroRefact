package com.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class BasicServiceController {

 private BasicService basicservice;


@GetMapping
("/save")
public Basic save(@RequestParam(name = "basic") Basic basic){
  return basicservice.save(basic);
}


@GetMapping
("/findByReportId")
public Basic findByReportId(@RequestParam(name = "id") Integer id){
  return basicservice.findByReportId(id);
}


}