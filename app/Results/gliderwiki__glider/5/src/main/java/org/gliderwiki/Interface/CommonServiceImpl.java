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


public int addFavorite(Integer weUserIdx,FavorityType type,int spaceIdx,int wikiIdx){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/addFavorite"))
    .queryParam("weUserIdx",weUserIdx)
    .queryParam("type",type)
    .queryParam("spaceIdx",spaceIdx)
    .queryParam("wikiIdx",wikiIdx)
;  int aux = restTemplate.getForObject(builder.toUriString(), int.class);

 return aux;
}


}