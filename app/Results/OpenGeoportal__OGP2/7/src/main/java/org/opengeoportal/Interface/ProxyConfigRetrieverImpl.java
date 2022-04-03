package org.opengeoportal.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.opengeoportal.Interface.ProxyConfigRetriever;
public class ProxyConfigRetrieverImpl implements ProxyConfigRetriever{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://12";


public String getInternalUrl(String type,String repository,String accessLevel,String locationField){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getInternalUrl"))
    .queryParam("type",type)
    .queryParam("repository",repository)
    .queryParam("accessLevel",accessLevel)
    .queryParam("locationField",locationField)
;  String aux = restTemplate.getForObject(builder.toUriString(), String.class);

 return aux;
}


}