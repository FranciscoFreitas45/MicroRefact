package com.uec.imonitor.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class EsClientController {

 private EsClient esclient;


@GetMapping
("/getTransportClient")
public TransportClient getTransportClient(){
  return esclient.getTransportClient();
}


}