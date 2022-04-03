package org.gliderwiki.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.gliderwiki.Interface.CommonService;
public class CommonServiceImpl implements CommonService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://1";


public WeUser getUserInfo(Integer weUserIdx){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getUserInfo"))
    .queryParam("weUserIdx",weUserIdx)
;  WeUser aux = restTemplate.getForObject(builder.toUriString(), WeUser.class);

 return aux;
}


public WeProfile getUserProfileInfo(Integer weUserIdx){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getUserProfileInfo"))
    .queryParam("weUserIdx",weUserIdx)
;  WeProfile aux = restTemplate.getForObject(builder.toUriString(), WeProfile.class);

 return aux;
}


public int insertWeFile(WeFile weFile){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/insertWeFile"))
    .queryParam("weFile",weFile)
;  int aux = restTemplate.getForObject(builder.toUriString(), int.class);

 return aux;
}


public void updateAllRead(int userIdx){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/updateAllRead"))
    .queryParam("userIdx",userIdx)
;
  restTemplate.put(builder.toUriString(), null);
}


}