package com.uec.imonitor.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class IRequestSiteServiceController {

 private IRequestSiteService irequestsiteservice;


@GetMapping
("/findByRequestId")
public List<RequestSiteEntity> findByRequestId(@RequestParam(name = "requestId") Integer requestId){
  return irequestsiteservice.findByRequestId(requestId);
}


}