package com.sobey.cmop.mvc.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class MonitorElbController {

 private MonitorElb monitorelb;

 private MonitorElb monitorelb;


@PutMapping
("/setNetworkElbItem")
public void setNetworkElbItem(@RequestParam(name = "networkElbItem") NetworkElbItem networkElbItem){
monitorelb.setNetworkElbItem(networkElbItem);
}


@PutMapping
("/setApply")
public void setApply(@RequestParam(name = "apply") Apply apply){
monitorelb.setApply(apply);
}


@PutMapping
("/setIdentifier")
public void setIdentifier(@RequestParam(name = "identifier") String identifier){
monitorelb.setIdentifier(identifier);
}


}