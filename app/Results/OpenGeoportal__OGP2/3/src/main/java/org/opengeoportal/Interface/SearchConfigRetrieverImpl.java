package org.opengeoportal.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.opengeoportal.Interface.SearchConfigRetriever;
public class SearchConfigRetrieverImpl implements SearchConfigRetriever{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://12";


public URL getInternalSearchUrl(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getInternalSearchUrl"))
;  URL aux = restTemplate.getForObject(builder.toUriString(), URL.class);

 return aux;
}


}