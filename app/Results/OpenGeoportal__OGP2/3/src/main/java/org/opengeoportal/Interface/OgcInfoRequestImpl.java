package org.opengeoportal.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.opengeoportal.Interface.OgcInfoRequest;
public class OgcInfoRequestImpl implements OgcInfoRequest{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://4";


public OwsInfo parseResponse(InputStream inputStream){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/parseResponse"))
    .queryParam("inputStream",inputStream)
;  OwsInfo aux = restTemplate.getForObject(builder.toUriString(), OwsInfo.class);

 return aux;
}


public String createRequest(String layerName){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/createRequest"))
    .queryParam("layerName",layerName)
;  String aux = restTemplate.getForObject(builder.toUriString(), String.class);

 return aux;
}


public String getMethod(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getMethod"))
;  String aux = restTemplate.getForObject(builder.toUriString(), String.class);

 return aux;
}


public String getOgcProtocol(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getOgcProtocol"))
;  String aux = restTemplate.getForObject(builder.toUriString(), String.class);

 return aux;
}


public Object toLowerCase(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/toLowerCase"))
    .queryParam("Object",Object)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


}