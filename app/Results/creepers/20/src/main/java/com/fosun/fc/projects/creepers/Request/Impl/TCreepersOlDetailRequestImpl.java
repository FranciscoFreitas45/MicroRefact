package com.fosun.fc.projects.creepers.Request.Impl;
 import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import com.fosun.fc.projects.creepers.DTO.TCreepersOlDetail;
import com.fosun.fc.projects.creepers.Request.TCreepersOlDetailRequest;
public class TCreepersOlDetailRequestImpl implements TCreepersOlDetailRequest{

 private RestTemplate restTemplate = new RestTemplate();;


public List<TCreepersOlDetail> getTCreepersOlDetails(Long id){
 List<TCreepersOlDetail> aux = restTemplate.getForObject("http://15/TCreepersAccountBak/{id}/TCreepersOlDetail/getTCreepersOlDetails",List<TCreepersOlDetail>.class,id);
return aux;
}


public TCreepersOlDetail removeTCreepersOlDetail(TCreepersOlDetail TCreepersOlDetail,Long id){
 TCreepersOlDetail aux = restTemplate.getForObject("http://15/TCreepersAccountBak/{id}/TCreepersOlDetail/removeTCreepersOlDetail?'TCreepersOlDetail'=TCreepersOlDetail&'id'=id',",TCreepersOlDetail.class,id);
return aux;
}


public void setTCreepersOlDetails(List<TCreepersOlDetail> TCreepersOlDetails,Long id){
 restTemplate.put("http://15/TCreepersAccountBak/{id}/TCreepersOlDetail/setTCreepersOlDetails",TCreepersOlDetails,id);
 return ;
}


public TCreepersOlDetail addTCreepersOlDetail(TCreepersOlDetail TCreepersOlDetail,Long id){
 TCreepersOlDetail aux = restTemplate.getForObject("http://15/TCreepersAccountBak/{id}/TCreepersOlDetail/addTCreepersOlDetail?'TCreepersOlDetail'=TCreepersOlDetail&'id'=id',",TCreepersOlDetail.class,id);
return aux;
}


}