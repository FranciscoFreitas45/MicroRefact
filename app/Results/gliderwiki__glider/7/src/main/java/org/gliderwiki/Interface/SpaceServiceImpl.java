package org.gliderwiki.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.gliderwiki.Interface.SpaceService;
public class SpaceServiceImpl implements SpaceService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://5";


public List<Map<String,String>> getRecentSpaceList(Integer weUserIdx,Integer startRow,Integer endRow){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getRecentSpaceList"))
    .queryParam("weUserIdx",weUserIdx)
    .queryParam("startRow",startRow)
    .queryParam("endRow",endRow)
;  List<Map<String,String>> aux = restTemplate.getForObject(builder.toUriString(), List<Map<String,String>>.class);

 return aux;
}


}