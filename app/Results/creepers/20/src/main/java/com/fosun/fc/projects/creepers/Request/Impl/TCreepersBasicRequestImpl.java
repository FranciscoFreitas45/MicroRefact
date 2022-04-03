package com.fosun.fc.projects.creepers.Request.Impl;
 import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import com.fosun.fc.projects.creepers.DTO.TCreepersBasic;
import com.fosun.fc.projects.creepers.Request.TCreepersBasicRequest;
public class TCreepersBasicRequestImpl implements TCreepersBasicRequest{

 private RestTemplate restTemplate = new RestTemplate();;


public List<TCreepersBasic> getTCreepersBasics(Long id){
 List<TCreepersBasic> aux = restTemplate.getForObject("http://12/TCreepersAccountBak/{id}/TCreepersBasic/getTCreepersBasics",List<TCreepersBasic>.class,id);
return aux;
}


public TCreepersBasic addTCreepersBasic(TCreepersBasic TCreepersBasic,Long id){
 TCreepersBasic aux = restTemplate.getForObject("http://12/TCreepersAccountBak/{id}/TCreepersBasic/addTCreepersBasic?'TCreepersBasic'=TCreepersBasic&'id'=id',",TCreepersBasic.class,id);
return aux;
}


public void setTCreepersBasics(List<TCreepersBasic> TCreepersBasics,Long id){
 restTemplate.put("http://12/TCreepersAccountBak/{id}/TCreepersBasic/setTCreepersBasics",TCreepersBasics,id);
 return ;
}


public TCreepersBasic removeTCreepersBasic(TCreepersBasic TCreepersBasic,Long id){
 TCreepersBasic aux = restTemplate.getForObject("http://12/TCreepersAccountBak/{id}/TCreepersBasic/removeTCreepersBasic?'TCreepersBasic'=TCreepersBasic&'id'=id',",TCreepersBasic.class,id);
return aux;
}


}