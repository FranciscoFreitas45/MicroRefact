package com.fosun.fc.projects.creepers.Request.Impl;
 import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import com.fosun.fc.projects.creepers.DTO.TCreepersGuarantee;
import com.fosun.fc.projects.creepers.Request.TCreepersGuaranteeRequest;
public class TCreepersGuaranteeRequestImpl implements TCreepersGuaranteeRequest{

 private RestTemplate restTemplate = new RestTemplate();;


public void setTCreepersGuarantees(List<TCreepersGuarantee> TCreepersGuarantees,Long id){
 restTemplate.put("http://15/TCreepersAccountBak/{id}/TCreepersGuarantee/setTCreepersGuarantees",TCreepersGuarantees,id);
 return ;
}


public TCreepersGuarantee addTCreepersGuarantee(TCreepersGuarantee TCreepersGuarantee,Long id){
 TCreepersGuarantee aux = restTemplate.getForObject("http://15/TCreepersAccountBak/{id}/TCreepersGuarantee/addTCreepersGuarantee?'TCreepersGuarantee'=TCreepersGuarantee&'id'=id',",TCreepersGuarantee.class,id);
return aux;
}


public List<TCreepersGuarantee> getTCreepersGuarantees(Long id){
 List<TCreepersGuarantee> aux = restTemplate.getForObject("http://15/TCreepersAccountBak/{id}/TCreepersGuarantee/getTCreepersGuarantees",List<TCreepersGuarantee>.class,id);
return aux;
}


public TCreepersGuarantee removeTCreepersGuarantee(TCreepersGuarantee TCreepersGuarantee,Long id){
 TCreepersGuarantee aux = restTemplate.getForObject("http://15/TCreepersAccountBak/{id}/TCreepersGuarantee/removeTCreepersGuarantee?'TCreepersGuarantee'=TCreepersGuarantee&'id'=id',",TCreepersGuarantee.class,id);
return aux;
}


}