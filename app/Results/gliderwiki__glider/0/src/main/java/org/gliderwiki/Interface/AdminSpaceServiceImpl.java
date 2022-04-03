package org.gliderwiki.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.gliderwiki.Interface.AdminSpaceService;
public class AdminSpaceServiceImpl implements AdminSpaceService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://7";


public List<WeSpace> getSpaceSearchList(WeSpace weSpace){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getSpaceSearchList"))
    .queryParam("weSpace",weSpace)
;  List<WeSpace> aux = restTemplate.getForObject(builder.toUriString(), List<WeSpace>.class);

 return aux;
}


public List<WeSpace> getSpaceListMonth(WeSpace space,Date month){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getSpaceListMonth"))
    .queryParam("space",space)
    .queryParam("month",month)
;  List<WeSpace> aux = restTemplate.getForObject(builder.toUriString(), List<WeSpace>.class);

 return aux;
}


public List<WeBbs> getBbsSearchList(WeBbs weBbs){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getBbsSearchList"))
    .queryParam("weBbs",weBbs)
;  List<WeBbs> aux = restTemplate.getForObject(builder.toUriString(), List<WeBbs>.class);

 return aux;
}


}