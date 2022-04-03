package com.cym.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class BakController {

 private Bak bak;

 private Bak bak;


@PutMapping
("/setTime")
public void setTime(@RequestParam(name = "time") String time){
bak.setTime(time);
}


@PutMapping
("/setContent")
public void setContent(@RequestParam(name = "content") String content){
bak.setContent(content);
}


}