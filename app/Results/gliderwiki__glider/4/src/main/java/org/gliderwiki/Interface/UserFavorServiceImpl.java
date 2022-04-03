package org.gliderwiki.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.gliderwiki.Interface.UserFavorService;
public class UserFavorServiceImpl implements UserFavorService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://13";


public List<WikiFavoriteVo> getMyFavoriteSpaceList(Integer weUserIdx){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getMyFavoriteSpaceList"))
    .queryParam("weUserIdx",weUserIdx)
;  List<WikiFavoriteVo> aux = restTemplate.getForObject(builder.toUriString(), List<WikiFavoriteVo>.class);

 return aux;
}


public List<WikiFavoriteVo> getMyFavoriteWikiList(Integer weUserIdx){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getMyFavoriteWikiList"))
    .queryParam("weUserIdx",weUserIdx)
;  List<WikiFavoriteVo> aux = restTemplate.getForObject(builder.toUriString(), List<WikiFavoriteVo>.class);

 return aux;
}


}