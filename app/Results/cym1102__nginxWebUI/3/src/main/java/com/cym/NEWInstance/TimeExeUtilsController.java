package com.cym.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class TimeExeUtilsController {

 private TimeExeUtils timeexeutils;


@GetMapping
("/execCMD")
public String execCMD(@RequestParam(name = "cmd") String cmd,@RequestParam(name = "envs") String[] envs,@RequestParam(name = "timeout") long timeout){
  return timeexeutils.execCMD(cmd,envs,timeout);
}


}