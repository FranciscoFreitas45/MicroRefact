package org.gliderwiki.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.gliderwiki.Interface.AdminWikiService;
public class AdminWikiServiceImpl implements AdminWikiService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://7";


public List<WeWiki> getWikiSearchList(String weUserNick,String weWikiTitle,String weWikiText,String weSpaceName){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getWikiSearchList"))
    .queryParam("weUserNick",weUserNick)
    .queryParam("weWikiTitle",weWikiTitle)
    .queryParam("weWikiText",weWikiText)
    .queryParam("weSpaceName",weSpaceName)
;  List<WeWiki> aux = restTemplate.getForObject(builder.toUriString(), List<WeWiki>.class);

 return aux;
}


}