package org.opengeoportal.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class HttpRequesterController {

 private HttpRequester httprequester;


@GetMapping
("/sendRequest")
public InputStream sendRequest(@RequestParam(name = "serviceURL") String serviceURL,@RequestParam(name = "requestString") String requestString,@RequestParam(name = "requestMethod") String requestMethod,@RequestParam(name = "contentType") String contentType){
  return httprequester.sendRequest(serviceURL,requestString,requestMethod,contentType);
}


@GetMapping
("/getStatus")
public int getStatus(){
  return httprequester.getStatus();
}


@GetMapping
("/getContentType")
public String getContentType(){
  return httprequester.getContentType();
}


@GetMapping
("/toLowerCase")
public Object toLowerCase(@RequestParam(name = "Object") Object Object){
  return httprequester.toLowerCase(Object);
}


@GetMapping
("/getHeaderValue")
public String getHeaderValue(@RequestParam(name = "headerName") String headerName){
  return httprequester.getHeaderValue(headerName);
}


}