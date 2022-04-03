package com.gs.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class RemindServiceController {

 private RemindService remindservice;


@GetMapping
("/selectRemind")
public List<Remind> selectRemind(){
  return remindservice.selectRemind();
}


@GetMapping
("/addRemind")
public int addRemind(@RequestParam(name = "remind") Remind remind){
  return remindservice.addRemind(remind);
}


}