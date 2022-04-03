package com.fosun.fc.projects.creepers.Request.Impl;
 import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import com.fosun.fc.projects.creepers.DTO.TCreepersPublicCivil;
import com.fosun.fc.projects.creepers.Request.TCreepersPublicCivilRequest;
public class TCreepersPublicCivilRequestImpl implements TCreepersPublicCivilRequest{

 private RestTemplate restTemplate = new RestTemplate();;


public TCreepersPublicCivil addTCreepersPublicCivil(TCreepersPublicCivil TCreepersPublicCivil,Long id){
 TCreepersPublicCivil aux = restTemplate.getForObject("http://15/TCreepersAccountBak/{id}/TCreepersPublicCivil/addTCreepersPublicCivil?'TCreepersPublicCivil'=TCreepersPublicCivil&'id'=id',",TCreepersPublicCivil.class,id);
return aux;
}


public TCreepersPublicCivil removeTCreepersPublicCivil(TCreepersPublicCivil TCreepersPublicCivil,Long id){
 TCreepersPublicCivil aux = restTemplate.getForObject("http://15/TCreepersAccountBak/{id}/TCreepersPublicCivil/removeTCreepersPublicCivil?'TCreepersPublicCivil'=TCreepersPublicCivil&'id'=id',",TCreepersPublicCivil.class,id);
return aux;
}


public List<TCreepersPublicCivil> getTCreepersPublicCivils(Long id){
 List<TCreepersPublicCivil> aux = restTemplate.getForObject("http://15/TCreepersAccountBak/{id}/TCreepersPublicCivil/getTCreepersPublicCivils",List<TCreepersPublicCivil>.class,id);
return aux;
}


public void setTCreepersPublicCivils(List<TCreepersPublicCivil> TCreepersPublicCivils,Long id){
 restTemplate.put("http://15/TCreepersAccountBak/{id}/TCreepersPublicCivil/setTCreepersPublicCivils",TCreepersPublicCivils,id);
 return ;
}


}