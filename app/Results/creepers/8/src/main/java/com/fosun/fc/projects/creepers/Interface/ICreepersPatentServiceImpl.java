package com.fosun.fc.projects.creepers.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.fosun.fc.projects.creepers.Interface.ICreepersPatentService;
public class ICreepersPatentServiceImpl implements ICreepersPatentService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://13";


public List<TCreepersPatent> findByMerName(String rptNo){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findByMerName"))
    .queryParam("rptNo",rptNo)
;  List<TCreepersPatent> aux = restTemplate.getForObject(builder.toUriString(), List<TCreepersPatent>.class);

 return aux;
}


}