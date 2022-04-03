package org.gliderwiki.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.gliderwiki.Interface.RequestManager;
public class RequestManagerImpl implements RequestManager{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://0";


public String getRemoteAddress(HttpServletRequest request){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getRemoteAddress"))
    .queryParam("request",request)
;  String aux = restTemplate.getForObject(builder.toUriString(), String.class);

 return aux;
}


}