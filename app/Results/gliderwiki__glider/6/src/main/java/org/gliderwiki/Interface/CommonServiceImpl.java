package org.gliderwiki.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.gliderwiki.Interface.CommonService;
public class CommonServiceImpl implements CommonService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://1";


public String realNotiView(int userIdx){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/realNotiView"))
    .queryParam("userIdx",userIdx)
;  String aux = restTemplate.getForObject(builder.toUriString(), String.class);

 return aux;
}


}