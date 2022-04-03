package com.byr.warehouse.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.byr.warehouse.Interface.ApplyEnterService;
public class ApplyEnterServiceImpl implements ApplyEnterService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://4";


public int getNumberOfTodayApplyEnter(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getNumberOfTodayApplyEnter"))
;  int aux = restTemplate.getForObject(builder.toUriString(), int.class);

 return aux;
}


}