package org.opengeoportal.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.opengeoportal.Interface.ProxyConfigRetriever;
public class ProxyConfigRetrieverImpl implements ProxyConfigRetriever{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://12";


public boolean hasProxy(String type,String repository,String accessLevel){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/hasProxy"))
    .queryParam("type",type)
    .queryParam("repository",repository)
    .queryParam("accessLevel",accessLevel)
;  boolean aux = restTemplate.getForObject(builder.toUriString(), boolean.class);

 return aux;
}


public String getInternalProxyUrl(String type,String repository,String accessLevel){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getInternalProxyUrl"))
    .queryParam("type",type)
    .queryParam("repository",repository)
    .queryParam("accessLevel",accessLevel)
;  String aux = restTemplate.getForObject(builder.toUriString(), String.class);

 return aux;
}


}