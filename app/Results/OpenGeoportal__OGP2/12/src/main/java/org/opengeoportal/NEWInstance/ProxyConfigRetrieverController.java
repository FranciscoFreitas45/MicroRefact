package org.opengeoportal.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class ProxyConfigRetrieverController {

 private ProxyConfigRetriever proxyconfigretriever;


@GetMapping
("/getInternalUrl")
public String getInternalUrl(@RequestParam(name = "type") String type,@RequestParam(name = "repository") String repository,@RequestParam(name = "accessLevel") String accessLevel,@RequestParam(name = "locationField") String locationField){
  return proxyconfigretriever.getInternalUrl(type,repository,accessLevel,locationField);
}


@GetMapping
("/getInternalProxyUrl")
public String getInternalProxyUrl(@RequestParam(name = "type") String type,@RequestParam(name = "repository") String repository,@RequestParam(name = "accessLevel") String accessLevel){
  return proxyconfigretriever.getInternalProxyUrl(type,repository,accessLevel);
}


@GetMapping
("/hasProxy")
public boolean hasProxy(@RequestParam(name = "type") String type,@RequestParam(name = "repository") String repository,@RequestParam(name = "accessLevel") String accessLevel){
  return proxyconfigretriever.hasProxy(type,repository,accessLevel);
}


}