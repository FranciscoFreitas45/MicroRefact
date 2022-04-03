package com.byr.warehouse.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.byr.warehouse.Interface.ApplyOutService;
public class ApplyOutServiceImpl implements ApplyOutService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://0";


public int getNumberOfTodayApplyEnter(){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getNumberOfTodayApplyEnter"))
;  int aux = restTemplate.getForObject(builder.toUriString(), int.class);

 return aux;
}


}