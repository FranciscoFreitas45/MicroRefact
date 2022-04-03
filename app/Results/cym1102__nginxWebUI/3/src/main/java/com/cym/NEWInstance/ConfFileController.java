package com.cym.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class ConfFileController {

 private ConfFile conffile;

 private ConfFile conffile;


@PutMapping
("/setConf")
public void setConf(@RequestParam(name = "conf") String conf){
conffile.setConf(conf);
}


@PutMapping
("/setName")
public void setName(@RequestParam(name = "name") String name){
conffile.setName(name);
}


}