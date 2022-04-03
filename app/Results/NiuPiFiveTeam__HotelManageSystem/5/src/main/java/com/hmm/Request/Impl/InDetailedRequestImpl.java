package com.hmm.Request.Impl;
 import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import com.hmm.DTO.InDetailed;
import com.hmm.Request.InDetailedRequest;
public class InDetailedRequestImpl implements InDetailedRequest{

 private RestTemplate restTemplate = new RestTemplate();;


public List<InDetailed> getInDetaileds(String inStorageId){
 List<InDetailed> aux = restTemplate.getForObject("http://9/InStorage/{id}/InDetailed/getInDetaileds",List<InDetailed>.class,inStorageId);
return aux;
}


public void setInDetaileds(List<InDetailed> inDetaileds,String inStorageId){
 restTemplate.put("http://9/InStorage/{id}/InDetailed/setInDetaileds",inDetaileds,inStorageId);
 return ;
}


}