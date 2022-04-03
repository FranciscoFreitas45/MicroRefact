package org.gliderwiki.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.gliderwiki.Interface.AdminKeywordService;
public class AdminKeywordServiceImpl implements AdminKeywordService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://0";


public int deleteKeywordWiki(Integer wikiIdx,Integer wikiRevision,Integer weUserIdx){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/deleteKeywordWiki"))
    .queryParam("wikiIdx",wikiIdx)
    .queryParam("wikiRevision",wikiRevision)
    .queryParam("weUserIdx",weUserIdx)
;  int aux = restTemplate.getForObject(builder.toUriString(), int.class);

 return aux;
}


}