package com.fosun.fc.projects.creepers.Request.Impl;
 import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import com.fosun.fc.projects.creepers.DTO.TCreepersPublicSanction;
import com.fosun.fc.projects.creepers.Request.TCreepersPublicSanctionRequest;
public class TCreepersPublicSanctionRequestImpl implements TCreepersPublicSanctionRequest{

 private RestTemplate restTemplate = new RestTemplate();;


public void setTCreepersPublicSanctions(List<TCreepersPublicSanction> TCreepersPublicSanctions,Long id){
 restTemplate.put("http://15/TCreepersAccountBak/{id}/TCreepersPublicSanction/setTCreepersPublicSanctions",TCreepersPublicSanctions,id);
 return ;
}


public List<TCreepersPublicSanction> getTCreepersPublicSanctions(Long id){
 List<TCreepersPublicSanction> aux = restTemplate.getForObject("http://15/TCreepersAccountBak/{id}/TCreepersPublicSanction/getTCreepersPublicSanctions",List<TCreepersPublicSanction>.class,id);
return aux;
}


public TCreepersPublicSanction removeTCreepersPublicSanction(TCreepersPublicSanction TCreepersPublicSanction,Long id){
 TCreepersPublicSanction aux = restTemplate.getForObject("http://15/TCreepersAccountBak/{id}/TCreepersPublicSanction/removeTCreepersPublicSanction?'TCreepersPublicSanction'=TCreepersPublicSanction&'id'=id',",TCreepersPublicSanction.class,id);
return aux;
}


public TCreepersPublicSanction addTCreepersPublicSanction(TCreepersPublicSanction TCreepersPublicSanction,Long id){
 TCreepersPublicSanction aux = restTemplate.getForObject("http://15/TCreepersAccountBak/{id}/TCreepersPublicSanction/addTCreepersPublicSanction?'TCreepersPublicSanction'=TCreepersPublicSanction&'id'=id',",TCreepersPublicSanction.class,id);
return aux;
}


}