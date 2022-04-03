package org.opengeoportal.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class OgcInfoRequestController {

 private OgcInfoRequest ogcinforequest;


@GetMapping
("/parseResponse")
public OwsInfo parseResponse(@RequestParam(name = "inputStream") InputStream inputStream){
  return ogcinforequest.parseResponse(inputStream);
}


@GetMapping
("/createRequest")
public String createRequest(@RequestParam(name = "layerName") String layerName){
  return ogcinforequest.createRequest(layerName);
}


@GetMapping
("/getMethod")
public String getMethod(){
  return ogcinforequest.getMethod();
}


@GetMapping
("/getOgcProtocol")
public String getOgcProtocol(){
  return ogcinforequest.getOgcProtocol();
}


@GetMapping
("/toLowerCase")
public Object toLowerCase(@RequestParam(name = "Object") Object Object){
  return ogcinforequest.toLowerCase(Object);
}


}