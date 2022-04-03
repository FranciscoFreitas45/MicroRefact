package com.fosun.fc.projects.creepers.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.fosun.fc.projects.creepers.Interface.ICreepersInsuranceService;
public class ICreepersInsuranceServiceImpl implements ICreepersInsuranceService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://16";


public void processByParam(CreepersLoginParamDTO param){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/processByParam"))
    .queryParam("param",param)
;
  restTemplate.put(builder.toUriString(), null);
}


}