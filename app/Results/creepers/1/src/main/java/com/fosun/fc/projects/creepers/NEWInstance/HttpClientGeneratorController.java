package com.fosun.fc.projects.creepers.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class HttpClientGeneratorController {

 private HttpClientGenerator httpclientgenerator;


@GetMapping
("/getClient")
public CloseableHttpClient getClient(@RequestParam(name = "site") Site site){
  return httpclientgenerator.getClient(site);
}


}