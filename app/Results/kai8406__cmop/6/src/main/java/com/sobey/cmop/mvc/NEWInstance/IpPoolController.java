package com.sobey.cmop.mvc.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class IpPoolController {

 private IpPool ippool;

 private IpPool ippool;


@PutMapping
("/setHostServer")
public void setHostServer(@RequestParam(name = "hostServer") HostServer hostServer){
ippool.setHostServer(hostServer);
}


}