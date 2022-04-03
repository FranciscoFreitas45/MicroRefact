package com.fosun.fc.projects.creepers.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.fosun.fc.projects.creepers.Interface.ICreepersTaxEvasionDetailService;
public class ICreepersTaxEvasionDetailServiceImpl implements ICreepersTaxEvasionDetailService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://9";


public List<TCreepersTaxEvasionDetail> findListByName(String name){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findListByName"))
    .queryParam("name",name)
;  List<TCreepersTaxEvasionDetail> aux = restTemplate.getForObject(builder.toUriString(), List<TCreepersTaxEvasionDetail>.class);

 return aux;
}


}