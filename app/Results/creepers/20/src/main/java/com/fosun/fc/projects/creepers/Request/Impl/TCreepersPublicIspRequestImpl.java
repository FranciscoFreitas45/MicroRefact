package com.fosun.fc.projects.creepers.Request.Impl;
 import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import com.fosun.fc.projects.creepers.DTO.TCreepersPublicIsp;
import com.fosun.fc.projects.creepers.Request.TCreepersPublicIspRequest;
public class TCreepersPublicIspRequestImpl implements TCreepersPublicIspRequest{

 private RestTemplate restTemplate = new RestTemplate();;


public TCreepersPublicIsp addTCreepersPublicIsp(TCreepersPublicIsp TCreepersPublicIsp,Long id){
 TCreepersPublicIsp aux = restTemplate.getForObject("http://15/TCreepersAccountBak/{id}/TCreepersPublicIsp/addTCreepersPublicIsp?'TCreepersPublicIsp'=TCreepersPublicIsp&'id'=id',",TCreepersPublicIsp.class,id);
return aux;
}


public List<TCreepersPublicIsp> getTCreepersPublicIsps(Long id){
 List<TCreepersPublicIsp> aux = restTemplate.getForObject("http://15/TCreepersAccountBak/{id}/TCreepersPublicIsp/getTCreepersPublicIsps",List<TCreepersPublicIsp>.class,id);
return aux;
}


public TCreepersPublicIsp removeTCreepersPublicIsp(TCreepersPublicIsp TCreepersPublicIsp,Long id){
 TCreepersPublicIsp aux = restTemplate.getForObject("http://15/TCreepersAccountBak/{id}/TCreepersPublicIsp/removeTCreepersPublicIsp?'TCreepersPublicIsp'=TCreepersPublicIsp&'id'=id',",TCreepersPublicIsp.class,id);
return aux;
}


public void setTCreepersPublicIsps(List<TCreepersPublicIsp> TCreepersPublicIsps,Long id){
 restTemplate.put("http://15/TCreepersAccountBak/{id}/TCreepersPublicIsp/setTCreepersPublicIsps",TCreepersPublicIsps,id);
 return ;
}


}