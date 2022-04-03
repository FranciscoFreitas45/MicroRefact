package org.gliderwiki.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.gliderwiki.Interface.NotifiationService;
public class NotifiationServiceImpl implements NotifiationService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://0";


public int getNewNotiCount(int userIdx){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getNewNotiCount"))
    .queryParam("userIdx",userIdx)
;  int aux = restTemplate.getForObject(builder.toUriString(), int.class);

 return aux;
}


}