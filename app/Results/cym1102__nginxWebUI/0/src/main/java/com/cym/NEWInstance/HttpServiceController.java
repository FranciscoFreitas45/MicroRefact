package com.cym.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class HttpServiceController {

 private HttpService httpservice;


@GetMapping
("/getName")
public Http getName(@RequestParam(name = "name") String name){
  return httpservice.getName(name);
}


}