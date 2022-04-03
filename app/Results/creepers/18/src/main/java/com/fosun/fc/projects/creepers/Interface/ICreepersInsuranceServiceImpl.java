package com.fosun.fc.projects.creepers.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.fosun.fc.projects.creepers.Interface.ICreepersInsuranceService;
public class ICreepersInsuranceServiceImpl implements ICreepersInsuranceService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://16";


public Map<String,Object> findListByCertNoForMap(String certNo){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findListByCertNoForMap"))
    .queryParam("certNo",certNo)
;  Map<String,Object> aux = restTemplate.getForObject(builder.toUriString(), Map<String,Object>.class);

 return aux;
}


}