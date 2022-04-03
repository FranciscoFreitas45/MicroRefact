package org.gliderwiki.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.gliderwiki.Interface.CommonService;
public class CommonServiceImpl implements CommonService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://1";


public WeWiki getWikiInfo(Integer weWikiIdx){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getWikiInfo"))
    .queryParam("weWikiIdx",weWikiIdx)
;  WeWiki aux = restTemplate.getForObject(builder.toUriString(), WeWiki.class);

 return aux;
}


}