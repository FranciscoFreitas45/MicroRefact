package com.uec.imonitor.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class BaseQueryResultController {

 private BaseQueryResult basequeryresult;

 private BaseQueryResult basequeryresult;


@PutMapping
("/setAggsMap")
public void setAggsMap(@RequestParam(name = "aggsMap") Map<String,Long> aggsMap){
basequeryresult.setAggsMap(aggsMap);
}


}