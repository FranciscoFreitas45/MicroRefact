package com.sobey.cmop.mvc.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class ServiceTagController {

 private ServiceTag servicetag;

 private ServiceTag servicetag;


@PutMapping
("/setStatus")
public void setStatus(@RequestParam(name = "status") Integer status){
servicetag.setStatus(status);
}


}