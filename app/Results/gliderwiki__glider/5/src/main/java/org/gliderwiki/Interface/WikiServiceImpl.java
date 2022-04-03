package org.gliderwiki.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.gliderwiki.Interface.WikiService;
public class WikiServiceImpl implements WikiService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://1";


public List<WeWiki> getWikiList(int spaceIdx){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getWikiList"))
    .queryParam("spaceIdx",spaceIdx)
;  List<WeWiki> aux = restTemplate.getForObject(builder.toUriString(), List<WeWiki>.class);

 return aux;
}


}