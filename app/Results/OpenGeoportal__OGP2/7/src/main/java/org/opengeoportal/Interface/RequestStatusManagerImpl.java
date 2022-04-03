package org.opengeoportal.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.opengeoportal.Interface.RequestStatusManager;
public class RequestStatusManagerImpl implements RequestStatusManager{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://6";


public ImageRequest getImageRequest(UUID fromString){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getImageRequest"))
    .queryParam("fromString",fromString)
;  ImageRequest aux = restTemplate.getForObject(builder.toUriString(), ImageRequest.class);

 return aux;
}


}