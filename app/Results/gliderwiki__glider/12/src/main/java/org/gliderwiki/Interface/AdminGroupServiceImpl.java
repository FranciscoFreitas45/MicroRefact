package org.gliderwiki.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.gliderwiki.Interface.AdminGroupService;
public class AdminGroupServiceImpl implements AdminGroupService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://3";


public int insertGroupUser(Map<Integer,Map> maps){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/insertGroupUser"))
    .queryParam("maps",maps)
;  int aux = restTemplate.getForObject(builder.toUriString(), int.class);

 return aux;
}


public int insertGroupInfo(Map<Integer,Map> maps){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/insertGroupInfo"))
    .queryParam("maps",maps)
;  int aux = restTemplate.getForObject(builder.toUriString(), int.class);

 return aux;
}


}