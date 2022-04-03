package com.empl.mgr.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class JSONReturnController {

 private JSONReturn jsonreturn;

 private JSONReturn jsonreturn;


@GetMapping
("/buildSuccess")
public JSONReturn buildSuccess(@RequestParam(name = "body") Object body){
  return jsonreturn.buildSuccess(body);
}


@GetMapping
("/buildSuccessWithEmptyBody")
public JSONReturn buildSuccessWithEmptyBody(){
  return jsonreturn.buildSuccessWithEmptyBody();
}


}