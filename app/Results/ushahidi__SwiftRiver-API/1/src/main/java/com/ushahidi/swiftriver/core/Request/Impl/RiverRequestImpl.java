package com.ushahidi.swiftriver.core.Request.Impl;
 import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import com.ushahidi.swiftriver.core.DTO.River;
import com.ushahidi.swiftriver.core.Request.RiverRequest;
public class RiverRequestImpl implements RiverRequest{

 private RestTemplate restTemplate = new RestTemplate();;


public River getRiver(Long idATXO){
 River aux = restTemplate.getForObject("http://2/Channel/{id}/River/getRiver",River.class,idATXO);
return aux;
}


public void setRiver(River river,Long idATXO){
 restTemplate.put("http://2/Channel/{id}/River/setRiver",river,idATXO);
 return ;
}


}