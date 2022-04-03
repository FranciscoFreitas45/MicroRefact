package org.sdrc.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.sdrc.Interface.UtTimeperiodRepository;
public class UtTimeperiodRepositoryImpl implements UtTimeperiodRepository{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://8";


public Object findLatestTimePeriodNId(Integer iusNid,Integer[] areaNid){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findLatestTimePeriodNId"))
    .queryParam("iusNid",iusNid)
    .queryParam("areaNid",areaNid)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


}