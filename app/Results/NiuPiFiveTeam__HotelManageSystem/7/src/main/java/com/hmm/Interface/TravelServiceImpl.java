package com.hmm.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.hmm.Interface.TravelService;
public class TravelServiceImpl implements TravelService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://6";


public float findTotalTravelAllowance(String userName){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findTotalTravelAllowance"))
    .queryParam("userName",userName)
;  float aux = restTemplate.getForObject(builder.toUriString(), float.class);

 return aux;
}


public Integer findTatalPersonTravel(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findTatalPersonTravel"))
;  Integer aux = restTemplate.getForObject(builder.toUriString(), Integer.class);

 return aux;
}


public List<Map<Object,Object>> findByyearAndOntudytimetravel(Integer year,String userName){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findByyearAndOntudytimetravel"))
    .queryParam("year",year)
    .queryParam("userName",userName)
;  List<Map<Object,Object>> aux = restTemplate.getForObject(builder.toUriString(), List<Map<Object,Object>>.class);

 return aux;
}


public List<Map<Object,Object>> findtravel(Integer year){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findtravel"))
    .queryParam("year",year)
;  List<Map<Object,Object>> aux = restTemplate.getForObject(builder.toUriString(), List<Map<Object,Object>>.class);

 return aux;
}


}