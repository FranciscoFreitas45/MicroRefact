package org.opengeoportal.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class OgpConfigRetrieverController {

 private OgpConfigRetriever ogpconfigretriever;


@GetMapping
("/getConfig")
public OgpConfig getConfig(){
  return ogpconfigretriever.getConfig();
}


}