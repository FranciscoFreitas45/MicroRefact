package org.sdrc.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.sdrc.Interface.UtIndicatorUnitSubgroupRepository;
public class UtIndicatorUnitSubgroupRepositoryImpl implements UtIndicatorUnitSubgroupRepository{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://15";


public List<Object[]> getIUS(int id){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getIUS"))
    .queryParam("id",id)
;  List<Object[]> aux = restTemplate.getForObject(builder.toUriString(), List<Object[]>.class);

 return aux;
}


}