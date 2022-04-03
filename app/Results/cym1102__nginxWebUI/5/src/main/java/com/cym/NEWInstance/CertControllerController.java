package com.cym.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class CertControllerController {

 private CertController certcontroller;


@GetMapping
("/apply")
public JsonResult apply(@RequestParam(name = "id") String id,@RequestParam(name = "type") String type){
  return certcontroller.apply(id,type);
}


}