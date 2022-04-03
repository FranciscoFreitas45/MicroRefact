package com.fosun.fc.projects.creepers.Request.Impl;
 import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import com.fosun.fc.projects.creepers.DTO.TCreepersPublicEnforcement;
import com.fosun.fc.projects.creepers.Request.TCreepersPublicEnforcementRequest;
public class TCreepersPublicEnforcementRequestImpl implements TCreepersPublicEnforcementRequest{

 private RestTemplate restTemplate = new RestTemplate();;


public void setTCreepersPublicEnforcements(List<TCreepersPublicEnforcement> TCreepersPublicEnforcements,Long id){
 restTemplate.put("http://15/TCreepersAccountBak/{id}/TCreepersPublicEnforcement/setTCreepersPublicEnforcements",TCreepersPublicEnforcements,id);
 return ;
}


public List<TCreepersPublicEnforcement> getTCreepersPublicEnforcements(Long id){
 List<TCreepersPublicEnforcement> aux = restTemplate.getForObject("http://15/TCreepersAccountBak/{id}/TCreepersPublicEnforcement/getTCreepersPublicEnforcements",List<TCreepersPublicEnforcement>.class,id);
return aux;
}


public TCreepersPublicEnforcement removeTCreepersPublicEnforcement(TCreepersPublicEnforcement TCreepersPublicEnforcement,Long id){
 TCreepersPublicEnforcement aux = restTemplate.getForObject("http://15/TCreepersAccountBak/{id}/TCreepersPublicEnforcement/removeTCreepersPublicEnforcement?'TCreepersPublicEnforcement'=TCreepersPublicEnforcement&'id'=id',",TCreepersPublicEnforcement.class,id);
return aux;
}


public TCreepersPublicEnforcement addTCreepersPublicEnforcement(TCreepersPublicEnforcement TCreepersPublicEnforcement,Long id){
 TCreepersPublicEnforcement aux = restTemplate.getForObject("http://15/TCreepersAccountBak/{id}/TCreepersPublicEnforcement/addTCreepersPublicEnforcement?'TCreepersPublicEnforcement'=TCreepersPublicEnforcement&'id'=id',",TCreepersPublicEnforcement.class,id);
return aux;
}


}