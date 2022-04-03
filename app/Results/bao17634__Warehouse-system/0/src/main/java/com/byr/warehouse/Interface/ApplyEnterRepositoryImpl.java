package com.byr.warehouse.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.byr.warehouse.Interface.ApplyEnterRepository;
public class ApplyEnterRepositoryImpl implements ApplyEnterRepository{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://4";


public List<ApplyEnter> getYestdayApplys(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getYestdayApplys"))
;  List<ApplyEnter> aux = restTemplate.getForObject(builder.toUriString(), List<ApplyEnter>.class);

 return aux;
}


}