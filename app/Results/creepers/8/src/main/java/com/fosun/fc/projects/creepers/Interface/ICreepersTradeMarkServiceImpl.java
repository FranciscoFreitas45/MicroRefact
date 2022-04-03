package com.fosun.fc.projects.creepers.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.fosun.fc.projects.creepers.Interface.ICreepersTradeMarkService;
public class ICreepersTradeMarkServiceImpl implements ICreepersTradeMarkService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://4";


public List<TCreepersTradeMark> findByMerName(String merName){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findByMerName"))
    .queryParam("merName",merName)
;  List<TCreepersTradeMark> aux = restTemplate.getForObject(builder.toUriString(), List<TCreepersTradeMark>.class);

 return aux;
}


}