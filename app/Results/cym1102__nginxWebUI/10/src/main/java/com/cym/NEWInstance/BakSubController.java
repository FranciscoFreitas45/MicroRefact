package com.cym.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class BakSubController {

 private BakSub baksub;

 private BakSub baksub;


@PutMapping
("/setBakId")
public void setBakId(@RequestParam(name = "bakId") String bakId){
baksub.setBakId(bakId);
}


@PutMapping
("/setName")
public void setName(@RequestParam(name = "name") String name){
baksub.setName(name);
}


@PutMapping
("/setContent")
public void setContent(@RequestParam(name = "content") String content){
baksub.setContent(content);
}


}