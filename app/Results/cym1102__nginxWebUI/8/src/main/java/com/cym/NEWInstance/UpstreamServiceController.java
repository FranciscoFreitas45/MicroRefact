package com.cym.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class UpstreamServiceController {

 private UpstreamService upstreamservice;


@GetMapping
("/getAllServer")
public List<UpstreamServer> getAllServer(){
  return upstreamservice.getAllServer();
}


@GetMapping
("/getListByProxyType")
public List<Upstream> getListByProxyType(@RequestParam(name = "proxyType") Integer proxyType){
  return upstreamservice.getListByProxyType(proxyType);
}


@GetMapping
("/getUpstreamServers")
public List<UpstreamServer> getUpstreamServers(@RequestParam(name = "id") String id){
  return upstreamservice.getUpstreamServers(id);
}


}