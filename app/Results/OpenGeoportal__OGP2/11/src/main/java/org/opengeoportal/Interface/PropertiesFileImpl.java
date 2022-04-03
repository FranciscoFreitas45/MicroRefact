package org.opengeoportal.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.opengeoportal.Interface.PropertiesFile;
public class PropertiesFileImpl implements PropertiesFile{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://12";


public Properties getProperties(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getProperties"))
;  Properties aux = restTemplate.getForObject(builder.toUriString(), Properties.class);

 return aux;
}


}