package com.fosun.fc.projects.creepers.Request.Impl;
 import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import com.fosun.fc.projects.creepers.DTO.TCreepersAccountBak;
import com.fosun.fc.projects.creepers.Request.TCreepersAccountBakRequest;
public class TCreepersAccountBakRequestImpl implements TCreepersAccountBakRequest{

 private RestTemplate restTemplate = new RestTemplate();;


public void setTCreepersAccountBak(TCreepersAccountBak TCreepersAccountBak,Long id){
 restTemplate.put("http://20/TCreepersCcDetail/{id}/TCreepersAccountBak/setTCreepersAccountBak",TCreepersAccountBak,id);
 return ;
}


public TCreepersAccountBak getTCreepersAccountBak(Long id){
 TCreepersAccountBak aux = restTemplate.getForObject("http://20/TCreepersCcDetail/{id}/TCreepersAccountBak/getTCreepersAccountBak",TCreepersAccountBak.class,id);
return aux;
}


}