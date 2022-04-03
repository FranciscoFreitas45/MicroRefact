package com.sobey.cmop.mvc.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class ResourcesController {

 private Resources resources;

 private Resources resources;


@PutMapping
("/setStatus")
public void setStatus(@RequestParam(name = "status") Integer status){
resources.setStatus(status);
}


@PutMapping
("/setServiceTag")
public void setServiceTag(@RequestParam(name = "serviceTag") ServiceTag serviceTag){
resources.setServiceTag(serviceTag);
}


@PutMapping
("/setIpAddress")
public void setIpAddress(@RequestParam(name = "ipAddress") String ipAddress){
resources.setIpAddress(ipAddress);
}


@PutMapping
("/setOldIp")
public void setOldIp(@RequestParam(name = "oldIp") String oldIp){
resources.setOldIp(oldIp);
}


}