package com.fosun.fc.projects.creepers.Request.Impl;
 import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import com.fosun.fc.projects.creepers.DTO.TCreepersPublicTax;
import com.fosun.fc.projects.creepers.Request.TCreepersPublicTaxRequest;
public class TCreepersPublicTaxRequestImpl implements TCreepersPublicTaxRequest{

 private RestTemplate restTemplate = new RestTemplate();;


public List<TCreepersPublicTax> getTCreepersPublicTaxs(Long id){
 List<TCreepersPublicTax> aux = restTemplate.getForObject("http://15/TCreepersAccountBak/{id}/TCreepersPublicTax/getTCreepersPublicTaxs",List<TCreepersPublicTax>.class,id);
return aux;
}


public void setTCreepersPublicTaxs(List<TCreepersPublicTax> TCreepersPublicTaxs,Long id){
 restTemplate.put("http://15/TCreepersAccountBak/{id}/TCreepersPublicTax/setTCreepersPublicTaxs",TCreepersPublicTaxs,id);
 return ;
}


public TCreepersPublicTax addTCreepersPublicTax(TCreepersPublicTax TCreepersPublicTax,Long id){
 TCreepersPublicTax aux = restTemplate.getForObject("http://15/TCreepersAccountBak/{id}/TCreepersPublicTax/addTCreepersPublicTax?'TCreepersPublicTax'=TCreepersPublicTax&'id'=id',",TCreepersPublicTax.class,id);
return aux;
}


public TCreepersPublicTax removeTCreepersPublicTax(TCreepersPublicTax TCreepersPublicTax,Long id){
 TCreepersPublicTax aux = restTemplate.getForObject("http://15/TCreepersAccountBak/{id}/TCreepersPublicTax/removeTCreepersPublicTax?'TCreepersPublicTax'=TCreepersPublicTax&'id'=id',",TCreepersPublicTax.class,id);
return aux;
}


}