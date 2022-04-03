package com.lingxiang2014.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class SnController {

 private Sn sn;

 private Sn sn;


@PutMapping
("/setLastValue")
public void setLastValue(@RequestParam(name = "lastValue") Long lastValue){
sn.setLastValue(lastValue);
}


}