package org.opengeoportal.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.opengeoportal.Interface.OgpConfigRetriever;
public class OgpConfigRetrieverImpl implements OgpConfigRetriever{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://11";


public OgpConfig getConfig(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getConfig"))
;  OgpConfig aux = restTemplate.getForObject(builder.toUriString(), OgpConfig.class);

 return aux;
}


}