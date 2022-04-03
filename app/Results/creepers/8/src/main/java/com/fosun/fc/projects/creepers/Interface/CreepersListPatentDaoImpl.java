package com.fosun.fc.projects.creepers.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.fosun.fc.projects.creepers.Interface.CreepersListPatentDao;
public class CreepersListPatentDaoImpl implements CreepersListPatentDao{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://13";


public List<TCreepersListPatent> queryListByMerName(String merName){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/queryListByMerName"))
    .queryParam("merName",merName)
;  List<TCreepersListPatent> aux = restTemplate.getForObject(builder.toUriString(), List<TCreepersListPatent>.class);

 return aux;
}


public Object findAll(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findAll"))
    .queryParam("Object",Object)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


public Object save(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/save"))
    .queryParam("Object",Object)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


public void updateListByMerName(String merName,String flag){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/updateListByMerName"))
    .queryParam("merName",merName)
    .queryParam("flag",flag)
;
  restTemplate.put(builder.toUriString(), null);
}


}