package org.gliderwiki.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.gliderwiki.Interface.SpaceService;
public class SpaceServiceImpl implements SpaceService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://5";


public SpaceInfoVo getSpaceInfo(int spaceIdx){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getSpaceInfo"))
    .queryParam("spaceIdx",spaceIdx)
;  SpaceInfoVo aux = restTemplate.getForObject(builder.toUriString(), SpaceInfoVo.class);

 return aux;
}


}