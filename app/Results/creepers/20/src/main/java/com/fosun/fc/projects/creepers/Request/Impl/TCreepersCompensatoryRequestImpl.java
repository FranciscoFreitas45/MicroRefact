package com.fosun.fc.projects.creepers.Request.Impl;
 import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import com.fosun.fc.projects.creepers.DTO.TCreepersCompensatory;
import com.fosun.fc.projects.creepers.Request.TCreepersCompensatoryRequest;
public class TCreepersCompensatoryRequestImpl implements TCreepersCompensatoryRequest{

 private RestTemplate restTemplate = new RestTemplate();;


public void setTCreepersCompensatories(List<TCreepersCompensatory> TCreepersCompensatories,Long id){
 restTemplate.put("http://15/TCreepersAccountBak/{id}/TCreepersCompensatory/setTCreepersCompensatories",TCreepersCompensatories,id);
 return ;
}


public TCreepersCompensatory removeTCreepersCompensatory(TCreepersCompensatory TCreepersCompensatory,Long id){
 TCreepersCompensatory aux = restTemplate.getForObject("http://15/TCreepersAccountBak/{id}/TCreepersCompensatory/removeTCreepersCompensatory?'TCreepersCompensatory'=TCreepersCompensatory&'id'=id',",TCreepersCompensatory.class,id);
return aux;
}


public TCreepersCompensatory addTCreepersCompensatory(TCreepersCompensatory TCreepersCompensatory,Long id){
 TCreepersCompensatory aux = restTemplate.getForObject("http://15/TCreepersAccountBak/{id}/TCreepersCompensatory/addTCreepersCompensatory?'TCreepersCompensatory'=TCreepersCompensatory&'id'=id',",TCreepersCompensatory.class,id);
return aux;
}


public List<TCreepersCompensatory> getTCreepersCompensatories(Long id){
 List<TCreepersCompensatory> aux = restTemplate.getForObject("http://15/TCreepersAccountBak/{id}/TCreepersCompensatory/getTCreepersCompensatories",List<TCreepersCompensatory>.class,id);
return aux;
}


}