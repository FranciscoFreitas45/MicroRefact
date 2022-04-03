package com.fosun.fc.projects.creepers.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.fosun.fc.projects.creepers.Interface.ICreepersFundService;
public class ICreepersFundServiceImpl implements ICreepersFundService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://17";


public Map<String,Object> findByLoginNameForMap(String loginName){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findByLoginNameForMap"))
    .queryParam("loginName",loginName)
;  Map<String,Object> aux = restTemplate.getForObject(builder.toUriString(), Map<String,Object>.class);

 return aux;
}


}