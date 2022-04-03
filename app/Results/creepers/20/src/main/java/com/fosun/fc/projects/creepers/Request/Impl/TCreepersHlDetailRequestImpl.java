package com.fosun.fc.projects.creepers.Request.Impl;
 import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import com.fosun.fc.projects.creepers.DTO.TCreepersHlDetail;
import com.fosun.fc.projects.creepers.Request.TCreepersHlDetailRequest;
public class TCreepersHlDetailRequestImpl implements TCreepersHlDetailRequest{

 private RestTemplate restTemplate = new RestTemplate();;


public TCreepersHlDetail addTCreepersHlDetail(TCreepersHlDetail TCreepersHlDetail,Long id){
 TCreepersHlDetail aux = restTemplate.getForObject("http://15/TCreepersAccountBak/{id}/TCreepersHlDetail/addTCreepersHlDetail?'TCreepersHlDetail'=TCreepersHlDetail&'id'=id',",TCreepersHlDetail.class,id);
return aux;
}


public List<TCreepersHlDetail> getTCreepersHlDetails(Long id){
 List<TCreepersHlDetail> aux = restTemplate.getForObject("http://15/TCreepersAccountBak/{id}/TCreepersHlDetail/getTCreepersHlDetails",List<TCreepersHlDetail>.class,id);
return aux;
}


public void setTCreepersHlDetails(List<TCreepersHlDetail> TCreepersHlDetails,Long id){
 restTemplate.put("http://15/TCreepersAccountBak/{id}/TCreepersHlDetail/setTCreepersHlDetails",TCreepersHlDetails,id);
 return ;
}


public TCreepersHlDetail removeTCreepersHlDetail(TCreepersHlDetail TCreepersHlDetail,Long id){
 TCreepersHlDetail aux = restTemplate.getForObject("http://15/TCreepersAccountBak/{id}/TCreepersHlDetail/removeTCreepersHlDetail?'TCreepersHlDetail'=TCreepersHlDetail&'id'=id',",TCreepersHlDetail.class,id);
return aux;
}


}