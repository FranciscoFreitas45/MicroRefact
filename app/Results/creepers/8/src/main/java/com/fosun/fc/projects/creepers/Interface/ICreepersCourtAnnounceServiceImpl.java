package com.fosun.fc.projects.creepers.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.fosun.fc.projects.creepers.Interface.ICreepersCourtAnnounceService;
public class ICreepersCourtAnnounceServiceImpl implements ICreepersCourtAnnounceService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://10";


public List<TCreepersCourtAnnounce> findByMerName(String merName){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findByMerName"))
    .queryParam("merName",merName)
;  List<TCreepersCourtAnnounce> aux = restTemplate.getForObject(builder.toUriString(), List<TCreepersCourtAnnounce>.class);

 return aux;
}


}