package com.fosun.fc.projects.creepers.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.fosun.fc.projects.creepers.Interface.ICreepersMedicalService;
public class ICreepersMedicalServiceImpl implements ICreepersMedicalService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://2";


public List<TCreepersMedical> findListByKey(String key){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findListByKey"))
    .queryParam("key",key)
;  List<TCreepersMedical> aux = restTemplate.getForObject(builder.toUriString(), List<TCreepersMedical>.class);

 return aux;
}


}