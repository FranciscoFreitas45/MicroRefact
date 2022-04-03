package com.cym.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class NginxApiControllerController {

 private NginxApiController nginxapicontroller;


@GetMapping
("/getNginxStartCmd")
public JsonResult<List<String>> getNginxStartCmd(){
  return nginxapicontroller.getNginxStartCmd();
}


@GetMapping
("/getNginxStopCmd")
public JsonResult<List<String>> getNginxStopCmd(){
  return nginxapicontroller.getNginxStopCmd();
}


}