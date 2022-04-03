package com.fosun.fc.projects.creepers.Request.Impl;
 import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import com.fosun.fc.projects.creepers.DTO.TCreepersCcDetail;
import com.fosun.fc.projects.creepers.Request.TCreepersCcDetailRequest;
public class TCreepersCcDetailRequestImpl implements TCreepersCcDetailRequest{

 private RestTemplate restTemplate = new RestTemplate();;


public void setTCreepersCcDetails(List<TCreepersCcDetail> TCreepersCcDetails,Long id){
 restTemplate.put("http://15/TCreepersAccountBak/{id}/TCreepersCcDetail/setTCreepersCcDetails",TCreepersCcDetails,id);
 return ;
}


public TCreepersCcDetail removeTCreepersCcDetail(TCreepersCcDetail TCreepersCcDetail,Long id){
 TCreepersCcDetail aux = restTemplate.getForObject("http://15/TCreepersAccountBak/{id}/TCreepersCcDetail/removeTCreepersCcDetail?'TCreepersCcDetail'=TCreepersCcDetail&'id'=id',",TCreepersCcDetail.class,id);
return aux;
}


public List<TCreepersCcDetail> getTCreepersCcDetails(Long id){
 List<TCreepersCcDetail> aux = restTemplate.getForObject("http://15/TCreepersAccountBak/{id}/TCreepersCcDetail/getTCreepersCcDetails",List<TCreepersCcDetail>.class,id);
return aux;
}


public TCreepersCcDetail addTCreepersCcDetail(TCreepersCcDetail TCreepersCcDetail,Long id){
 TCreepersCcDetail aux = restTemplate.getForObject("http://15/TCreepersAccountBak/{id}/TCreepersCcDetail/addTCreepersCcDetail?'TCreepersCcDetail'=TCreepersCcDetail&'id'=id',",TCreepersCcDetail.class,id);
return aux;
}


}