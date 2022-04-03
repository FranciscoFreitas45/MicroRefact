package org.gliderwiki.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.gliderwiki.Interface.CommonService;
public class CommonServiceImpl implements CommonService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://1";


public List<WikiVo> getWikiSearchList(String wiki_text){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getWikiSearchList"))
    .queryParam("wiki_text",wiki_text)
;  List<WikiVo> aux = restTemplate.getForObject(builder.toUriString(), List<WikiVo>.class);

 return aux;
}


public int delFavorite(Integer weUserIdx,FavorityType type,Integer addIdx){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/delFavorite"))
    .queryParam("weUserIdx",weUserIdx)
    .queryParam("type",type)
    .queryParam("addIdx",addIdx)
;  int aux = restTemplate.getForObject(builder.toUriString(), int.class);

 return aux;
}


}