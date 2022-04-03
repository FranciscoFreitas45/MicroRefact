package com.fosun.fc.projects.creepers.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.fosun.fc.projects.creepers.Interface.ICreepersCourtCorpBondsService;
public class ICreepersCourtCorpBondsServiceImpl implements ICreepersCourtCorpBondsService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://7";


public List<TCreepersCourtCorpBonds> findListByName(String name){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findListByName"))
    .queryParam("name",name)
;  List<TCreepersCourtCorpBonds> aux = restTemplate.getForObject(builder.toUriString(), List<TCreepersCourtCorpBonds>.class);

 return aux;
}


}