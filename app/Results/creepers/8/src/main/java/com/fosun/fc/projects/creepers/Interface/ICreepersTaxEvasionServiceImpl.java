package com.fosun.fc.projects.creepers.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.fosun.fc.projects.creepers.Interface.ICreepersTaxEvasionService;
public class ICreepersTaxEvasionServiceImpl implements ICreepersTaxEvasionService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://9";


public List<TCreepersTaxEvasion> findListByName(String name){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findListByName"))
    .queryParam("name",name)
;  List<TCreepersTaxEvasion> aux = restTemplate.getForObject(builder.toUriString(), List<TCreepersTaxEvasion>.class);

 return aux;
}


}