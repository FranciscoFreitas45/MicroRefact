package com.cym.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class ConfExtController {

 private ConfExt confext;

 private ConfExt confext;


@PutMapping
("/setConf")
public void setConf(@RequestParam(name = "conf") String conf){
confext.setConf(conf);
}


}