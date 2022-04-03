package org.opengeoportal.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.opengeoportal.Interface.MetadataRetriever;
public class MetadataRetrieverImpl implements MetadataRetriever{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://9";


public String getContactName(String layerID){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getContactName"))
    .queryParam("layerID",layerID)
;  String aux = restTemplate.getForObject(builder.toUriString(), String.class);

 return aux;
}


public String getContactAddress(String layerID){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getContactAddress"))
    .queryParam("layerID",layerID)
;  String aux = restTemplate.getForObject(builder.toUriString(), String.class);

 return aux;
}


public String getContactPhoneNumber(String layerId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getContactPhoneNumber"))
    .queryParam("layerId",layerId)
;  String aux = restTemplate.getForObject(builder.toUriString(), String.class);

 return aux;
}


}