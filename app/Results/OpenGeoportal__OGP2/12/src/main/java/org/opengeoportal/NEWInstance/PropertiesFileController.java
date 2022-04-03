package org.opengeoportal.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class PropertiesFileController {

 private PropertiesFile propertiesfile;


@GetMapping
("/getProperties")
public Properties getProperties(){
  return propertiesfile.getProperties();
}


}