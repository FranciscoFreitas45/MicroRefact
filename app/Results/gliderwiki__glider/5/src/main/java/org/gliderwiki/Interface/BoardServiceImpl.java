package org.gliderwiki.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.gliderwiki.Interface.BoardService;
public class BoardServiceImpl implements BoardService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://0";


public List<WeBbs> getRecentList(int spaceIdx){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getRecentList"))
    .queryParam("spaceIdx",spaceIdx)
;  List<WeBbs> aux = restTemplate.getForObject(builder.toUriString(), List<WeBbs>.class);

 return aux;
}


}